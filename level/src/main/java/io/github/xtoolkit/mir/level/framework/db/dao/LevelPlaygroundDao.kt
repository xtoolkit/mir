package io.github.xtoolkit.mir.level.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.xtoolkit.mir.level.framework.db.entity.LevelPlaygroundEntity

@Dao
interface LevelPlaygroundDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(levelPlaygroundEntity: LevelPlaygroundEntity): Long

    @Query("select * from level_playgrounds where id = :id")
    suspend fun get(id: Int): LevelPlaygroundEntity?

    @Query("select * from level_playgrounds where levelId = :levelId")
    suspend fun getLevelPlayground(levelId: Int): List<LevelPlaygroundEntity>
}