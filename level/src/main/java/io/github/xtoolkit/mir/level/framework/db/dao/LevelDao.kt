package io.github.xtoolkit.mir.level.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.xtoolkit.mir.level.framework.db.entity.LevelEntity

@Dao
interface LevelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(levelEntity: LevelEntity): Long

    @Query("select * from levels where id = :id")
    suspend fun getById(id: Int): LevelEntity?

    @Query("select * from levels")
    suspend fun getAll(): List<LevelEntity>
}