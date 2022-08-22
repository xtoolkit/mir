package io.github.xtoolkit.mir.playground.core.data.repository

import io.github.xtoolkit.mir.playground.core.data.datasource.PlaygroundDS
import io.github.xtoolkit.mir.playground.core.domain.Playground

class PlaygroundRepo(private val playgroundDS: PlaygroundDS) {
    suspend fun addPlayground(playground: Playground) = playgroundDS.add(playground)

    suspend fun getPlaygroundByID(id: Int) = playgroundDS.getById(id)
}