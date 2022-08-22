package io.github.xtoolkit.mir.level.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "levels")
data class LevelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String
)