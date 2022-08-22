package io.github.xtoolkit.mir.user.core.data.datasource

import io.github.xtoolkit.mir.user.core.domain.History
import io.github.xtoolkit.mir.user.core.domain.User

interface HistoryDS {
    suspend fun add(history: History): Result<History>
    suspend fun find(userId: Int, playgroundId: Int): Result<History>
    suspend fun update(history: History): Result<History>
    suspend fun getUserHistory(user: User): Result<List<History>>
}