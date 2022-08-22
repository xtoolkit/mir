package io.github.xtoolkit.mir.user.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.xtoolkit.mir.user.framework.db.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(userEntity: UserEntity): Long

    @Query("select * from users where id = :userId")
    suspend fun getUserById(userId: Int): UserEntity

    @Query("select * from users")
    suspend fun getAll(): List<UserEntity>
}