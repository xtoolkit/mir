package io.github.xtoolkit.mir.user.core.data.repository

import android.os.Build
import io.github.xtoolkit.mir.user.core.data.datasource.UserDS
import io.github.xtoolkit.mir.user.core.domain.User

class UserRepo(private val userDS: UserDS) {
    suspend fun getCurrentUser() = try {
        val users = userDS.getAll().getOrThrow()
        if (users.isEmpty()) Result.success(userDS.add(User(0, Build.DEVICE)).getOrThrow())
        else Result.success(users[0])
    } catch (e: Exception) {
        Result.failure(e)
    }
}