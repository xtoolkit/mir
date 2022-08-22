package io.github.xtoolkit.mir.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.xtoolkit.mir.level.framework.db.dao.LevelDao
import io.github.xtoolkit.mir.level.framework.db.dao.LevelPlaygroundDao
import io.github.xtoolkit.mir.level.framework.db.entity.LevelEntity
import io.github.xtoolkit.mir.level.framework.db.entity.LevelPlaygroundEntity
import io.github.xtoolkit.mir.playground.framework.db.dao.PlaygroundDao
import io.github.xtoolkit.mir.playground.framework.db.entity.PlaygroundEntity
import io.github.xtoolkit.mir.user.framework.db.dao.HistoryDao
import io.github.xtoolkit.mir.user.framework.db.dao.UserDao
import io.github.xtoolkit.mir.user.framework.db.entity.HistoryEntity
import io.github.xtoolkit.mir.user.framework.db.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        HistoryEntity::class,
        PlaygroundEntity::class,
        LevelEntity::class,
        LevelPlaygroundEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun historyDao(): HistoryDao
    abstract fun playgroundDao(): PlaygroundDao
    abstract fun levelDao(): LevelDao
    abstract fun levelPlaygroundDao(): LevelPlaygroundDao
}