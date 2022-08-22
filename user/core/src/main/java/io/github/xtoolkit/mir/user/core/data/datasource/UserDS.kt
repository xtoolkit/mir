package io.github.xtoolkit.mir.user.core.data.datasource

import io.github.xtoolkit.mir.user.core.domain.User

interface UserDS {
    suspend fun add(user: User): Result<User>
    suspend fun getAll(): Result<List<User>>
}