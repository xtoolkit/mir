package io.github.xtoolkit.mir.playground.framework.db.entity.converter

import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.playground.framework.db.entity.PlaygroundEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaygroundConverterKtTest {
    private val playgroundFakeId = 7

    private val playgroundFakeWords = listOf("AB", "CD", "EF")

    @Test
    fun givenDomainPlayground_whenToEntityCalled_thenEntityPlaygroundReturned() {
        // Arrange
        val playground = Playground(playgroundFakeId, playgroundFakeWords)

        // Act
        val result = playground.toEntity()

        // Assert
        assertEquals(playgroundFakeId, result.id)
        assertEquals(playgroundFakeWords.joinToString(","), result.words)
    }

    @Test
    fun givenEntityPlayground_whenToDomainCalled_thenDomainPlaygroundReturned() {
        // Arrange
        val playground = PlaygroundEntity(playgroundFakeId, playgroundFakeWords.joinToString(","))

        // Act
        val result = playground.toDomain()

        // Assert
        assertEquals(playgroundFakeId, result.id)
        assertEquals(
            playgroundFakeWords.joinToString(","),
            result.words.joinToString(",")
        )
    }
}