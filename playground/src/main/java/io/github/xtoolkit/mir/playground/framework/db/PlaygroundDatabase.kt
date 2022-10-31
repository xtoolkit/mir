package io.github.xtoolkit.mir.playground.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.xtoolkit.mir.playground.framework.db.dao.PlaygroundDao
import io.github.xtoolkit.mir.playground.framework.db.entity.PlaygroundEntity

@Database(
    entities = [PlaygroundEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PlaygroundDatabase : RoomDatabase() {
    abstract fun playgroundDao(): PlaygroundDao
}