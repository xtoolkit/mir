package io.github.xtoolkit.mir.playground.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.xtoolkit.mir.playground.framework.db.entity.PlaygroundEntity

@Dao
interface PlaygroundDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(playgroundEntity: PlaygroundEntity): Long

    @Query("select * from playgrounds where id = :id")
    suspend fun getById(id: Int): PlaygroundEntity?
}