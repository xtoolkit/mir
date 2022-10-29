package io.github.xtoolkit.mir.playground.core.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PlaygroundTest {
    private val playgroundFakeId = 7

    private val playgroundFakeWords = listOf("AB", "CD", "EF")

    private lateinit var playground: Playground

    @BeforeEach
    fun setUp() {
        playground = Playground(playgroundFakeId, playgroundFakeWords)
    }

    @Test
    fun whenPlaygroundGetId_thenPlaygroundIdReturned() {
        assertEquals(playgroundFakeId, playground.id)
    }

    @Test
    fun whenPlaygroundGetWords_thenPlaygroundWordsReturned() {
        assertEquals(playgroundFakeWords, playground.words)
    }
}