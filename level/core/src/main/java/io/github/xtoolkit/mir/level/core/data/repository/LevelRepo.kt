package io.github.xtoolkit.mir.level.core.data.repository

import io.github.xtoolkit.mir.level.core.data.datasource.LevelDS
import io.github.xtoolkit.mir.level.core.domain.Level

class LevelRepo(private val levelDS: LevelDS) {
    suspend fun addLevel(level: Level) = levelDS.add(level)

    suspend fun getLevels() = levelDS.getAll()
}