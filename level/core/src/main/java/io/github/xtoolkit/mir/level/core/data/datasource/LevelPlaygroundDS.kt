package io.github.xtoolkit.mir.level.core.data.datasource

import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.level.core.domain.LevelPlayground
import io.github.xtoolkit.mir.playground.core.domain.Playground

interface LevelPlaygroundDS {
    suspend fun add(level: Level, playground: Playground): Result<LevelPlayground>

    suspend fun getLevelPlayground(level: Level): Result<List<LevelPlayground>>
}