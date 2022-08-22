package io.github.xtoolkit.mir.user.framework.db.ds

import io.github.xtoolkit.mir.user.core.data.datasource.UserDS
import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.user.framework.db.dao.UserDao
import io.github.xtoolkit.mir.user.framework.db.entity.contverter.toDomain
import io.github.xtoolkit.mir.user.framework.db.entity.contverter.toEntity

class UserLocalDS(private val userDao: UserDao) : UserDS {
    override suspend fun add(user: User) = try {
        val userId = userDao.add(user.toEntity())
        Result.success(userDao.getUserById(userId.toInt()).toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getAll() = Result.success(
        userDao.getAll().map { it.toDomain() }
    )
}