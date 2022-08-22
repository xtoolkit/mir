package io.github.xtoolkit.mir.level.presentation.multi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.level.core.interactor.AddLevelPlaygroundUC
import io.github.xtoolkit.mir.level.core.interactor.AddLevelUC
import io.github.xtoolkit.mir.level.core.interactor.GetLevelPlaygroundUC
import io.github.xtoolkit.mir.level.core.interactor.GetLevelsUC
import io.github.xtoolkit.mir.playground.core.interactor.AddPlaygroundUC
import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.user.core.interactor.GetCurrentUserUC
import io.github.xtoolkit.mir.user.core.interactor.GetUserHistoryUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MultiLevelViewModel(
    private val getCurrentUserUC: GetCurrentUserUC,
    private val getLevelsUC: GetLevelsUC,
    private val getLevelPlaygroundUC: GetLevelPlaygroundUC,
    private val getUserHistoryUC: GetUserHistoryUC,
    private val addLevelUC: AddLevelUC,
    private val addPlaygroundUC: AddPlaygroundUC,
    private val addLevelPlaygroundUC: AddLevelPlaygroundUC
) : ViewModel() {
    var user by mutableStateOf<User?>(null)
    val levels = mutableStateListOf<Pair<Level, List<Pair<Int, Boolean>>>>()

    private suspend fun syncLevels() = getLevelsUC(Unit).onSuccess {
        val histories = getUserHistoryUC(user!!).getOrThrow()
        levels.clear()
        var available = true
        it.forEachIndexed { levelIndex, level ->
            val playgrounds = getLevelPlaygroundUC(level).getOrThrow()
            levels.add(level to playgrounds.mapIndexed { i, x ->
                val xAvailable = available
                if (available) {
                    available = if (levelIndex == 1 && i == 1) true
                    else histories.find { h -> h.userId == user!!.id && h.playgroundId == x.playgroundId }
                        ?.done ?: false
                }
                x.playgroundId to xAvailable
            })
        }
    }

    private suspend fun initialLevels() = try {
        initialLevels.forEach {
            val level = addLevelUC(it.first).getOrThrow()
            it.second.forEach { item ->
                val playground = addPlaygroundUC(item).getOrThrow()
                addLevelPlaygroundUC(level to playground)
            }
        }
    } catch (e: Exception) {
    }

    fun requestSyncLevel() = viewModelScope.launch(Dispatchers.IO) {
        syncLevels()
    }

    fun start() = viewModelScope.launch(Dispatchers.IO) {
        if (user == null) {
            getCurrentUserUC(Unit).onSuccess { user = it }
            syncLevels().onSuccess {
                if (levels.isEmpty()) {
                    initialLevels()
                    syncLevels()
                }
            }
        }
    }
}