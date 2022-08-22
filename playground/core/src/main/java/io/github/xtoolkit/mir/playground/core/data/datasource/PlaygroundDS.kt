package io.github.xtoolkit.mir.playground.core.data.datasource

import io.github.xtoolkit.mir.playground.core.domain.Playground

interface PlaygroundDS {
    suspend fun add(playground: Playground): Result<Playground>
    suspend fun getById(id: Int): Result<Playground>
}