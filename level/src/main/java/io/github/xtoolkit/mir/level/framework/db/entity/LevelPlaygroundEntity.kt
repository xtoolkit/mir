package io.github.xtoolkit.mir.level.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "level_playgrounds")
data class LevelPlaygroundEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val levelId: Int,
    val playgroundId: Int
)
