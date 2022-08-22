package io.github.xtoolkit.mir.playground.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playgrounds")
data class PlaygroundEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val words: String
)