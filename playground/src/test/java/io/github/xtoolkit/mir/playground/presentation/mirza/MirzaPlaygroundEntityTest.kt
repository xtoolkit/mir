package io.github.xtoolkit.mir.playground.presentation.mirza

import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.playground.presentation.mirza.converter.toMirzaPlaygroundEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class MirzaPlaygroundEntityTest {
    private val playgroundFakeId = 7

    private val playgroundFakeWords = listOf("AB", "CD", "EF")

    private val playgroundFakeChars = "ABCDEF".toList()

    @Test
    fun givenDomainPlayground_whenToMirzaPlaygroundEntityCalled_thenMirzaPlaygroundEntityReturned() {
        // Arrange
        val playground = Playground(playgroundFakeId, playgroundFakeWords)

        // Act
        val result = playground.toMirzaPlaygroundEntity()

        // Assert
        assertEquals(playgroundFakeId, result.id)
        assertEquals(playgroundFakeWords.sortedBy { it.length }, result.words)
        assertEquals(playgroundFakeChars.toString(), result.characters.toString())
    }
}