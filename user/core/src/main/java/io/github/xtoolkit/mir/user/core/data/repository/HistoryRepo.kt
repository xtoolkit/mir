package io.github.xtoolkit.mir.user.core.data.repository

import io.github.xtoolkit.mir.user.core.data.datasource.HistoryDS
import io.github.xtoolkit.mir.user.core.domain.History
import io.github.xtoolkit.mir.user.core.domain.User

class HistoryRepo(private val historyDS: HistoryDS) {
    suspend fun syncHistory(history: History) = historyDS.find(history.userId, history.playgroundId)
        .onSuccess { return historyDS.update(history.copy(id = it.id)) }
        .onFailure { return historyDS.add(history) }

    suspend fun getUserHistory(user: User) = historyDS.getUserHistory(user)
}