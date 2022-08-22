package io.github.xtoolkit.mir.level.core.data.repository

import io.github.xtoolkit.mir.level.core.data.datasource.LevelPlaygroundDS
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.playground.core.domain.Playground

class LevelPlaygroundRepo(private val levelPlaygroundDS: LevelPlaygroundDS) {
    suspend fun addLevelPlayground(level: Level, playground: Playground) =
        levelPlaygroundDS.add(level, playground)

    suspend fun getLevelPlayground(level: Level) = levelPlaygroundDS.getLevelPlayground(level)
}