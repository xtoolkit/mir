package io.github.xtoolkit.mir.user.framework.db.ds

import io.github.xtoolkit.mir.user.core.data.datasource.HistoryDS
import io.github.xtoolkit.mir.user.core.domain.History
import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.user.framework.db.dao.HistoryDao
import io.github.xtoolkit.mir.user.framework.db.entity.contverter.toDomain
import io.github.xtoolkit.mir.user.framework.db.entity.contverter.toEntity

class HistoryLocalDS(private val historyDao: HistoryDao) : HistoryDS {
    private suspend fun getByID(id: Int) = historyDao.getHistoryById(id)!!.toDomain()

    override suspend fun add(history: History) = try {
        val historyId = historyDao.add(history.toEntity())

        Result.success(getByID(historyId.toInt()))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun find(userId: Int, playgroundId: Int): Result<History> {
        val x = historyDao.findByUserPlayground(userId, playgroundId)
        if (x != null) return Result.success(x.toDomain())
        return Result.failure(Exception("Not Found"))
    }

    override suspend fun update(history: History) = try {
        historyDao.update(history.toEntity())
        Result.success(getByID(history.id))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getUserHistory(user: User) = Result.success(
        historyDao.getByUserId(user.id).map { it.toDomain() }
    )
}