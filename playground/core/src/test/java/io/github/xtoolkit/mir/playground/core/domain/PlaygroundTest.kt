package io.github.xtoolkit.mir.playground.core.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlaygroundTest {
    private val playgroundFakeId = 7

    private val playgroundFakeWords = listOf("AB", "CD", "EF")

    private lateinit var playground: Playground

    @Before
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