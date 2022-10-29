package io.github.xtoolkit.mir.playground.core.interactor

import io.github.xtoolkit.mir.playground.core.data.repository.PlaygroundRepo
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class GetPlaygroundByIdUCTest {
    private lateinit var subject: GetPlaygroundByIdUC

    @MockK
    private lateinit var mockPlayground: Playground

    @MockK
    private lateinit var mockPlaygroundRepo: PlaygroundRepo

    @BeforeEach
    fun setUp() {
        subject = GetPlaygroundByIdUC(mockPlaygroundRepo)
        every { mockPlayground.id } returns 7
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenPlaygroundId_whenGetPlaygroundByIdUCInvoked_thenPlaygroundReturnedWithMatchId() =
        runTest {
            // Arrange
            val playgroundId = 7
            coEvery { mockPlaygroundRepo.getPlaygroundByID(playgroundId) } returns Result.success(
                mockPlayground
            )

            // Act
            val result = subject(playgroundId).getOrThrow()

            // Assert
            assertEquals(playgroundId, result.id)

            // Verify
            coVerify(exactly = 1) { mockPlaygroundRepo.getPlaygroundByID(playgroundId) }
            coVerify(exactly = 1) { mockPlayground.id }
            confirmVerified(
                mockPlayground,
                mockPlaygroundRepo
            )
        }
}