package io.github.xtoolkit.mir.level.core.data.datasource

import io.github.xtoolkit.mir.level.core.domain.Level

interface LevelDS {
    suspend fun add(level: Level): Result<Level>
    suspend fun get(level: Level): Result<Level>
    suspend fun getAll(): Result<List<Level>>
}