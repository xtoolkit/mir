package io.github.xtoolkit.mir.level.presentation.mirza

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.playground.core.interactor.GetPlaygroundByIdUC
import io.github.xtoolkit.mir.user.core.domain.History
import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.user.core.interactor.GetUserHistoryUC
import io.github.xtoolkit.mir.user.core.interactor.SyncHistoryUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LevelMirzaViewModel(
    private val getPlaygroundByIdUC: GetPlaygroundByIdUC,
    private val syncHistoryUC: SyncHistoryUC,
    private val getUserHistoryUC: GetUserHistoryUC
) : ViewModel() {
    private var playgroundId = 0
    private var isDone = false
    var playground by mutableStateOf<Playground?>(null)
    var user by mutableStateOf<User?>(null)
    val found = mutableStateListOf<String>()

    fun updateHistory() = viewModelScope.launch(Dispatchers.IO) {
        playground?.let {
            syncHistoryUC(
                History(
                    id = 0,
                    userId = user!!.id,
                    playgroundId = it.id,
                    found = found,
                    done = if (isDone) true else it.words.size == found.size
                )
            )
        }
    }

    fun start(localUser: User, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        if (user == null) user = localUser
        if (playground == null || playgroundId != id) {
            playground = null
            getPlaygroundByIdUC(id).onSuccess {
                found.clear()
                getUserHistoryUC(user!!).onSuccess { list ->
                    list.find { item -> item.playgroundId == it.id }?.let { x ->
                        found.addAll(x.found)
                        isDone = x.done
                    }
                }
                playground = it
                playgroundId = it.id
            }
        }
    }
}