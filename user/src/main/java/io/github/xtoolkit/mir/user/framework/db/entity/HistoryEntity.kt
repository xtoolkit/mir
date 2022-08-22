package io.github.xtoolkit.mir.user.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "histories")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val playgroundId: Int,
    val found: String,
    val done: Boolean = false
)
