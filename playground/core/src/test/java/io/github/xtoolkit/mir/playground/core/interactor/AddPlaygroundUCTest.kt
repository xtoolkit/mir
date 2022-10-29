package io.github.xtoolkit.mir.playground.core.interactor

import io.github.xtoolkit.mir.playground.core.data.repository.PlaygroundRepo
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class AddPlaygroundUCTest {
    private lateinit var subject: AddPlaygroundUC

    @MockK
    private lateinit var mockPlayground: Playground

    @MockK
    private lateinit var mockPlaygroundRepo: PlaygroundRepo

    @BeforeEach
    fun setUp() {
        subject = AddPlaygroundUC(mockPlaygroundRepo)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenPlaygroundCandidate_whenAddPlaygroundUCInvoked_thenNewPlaygroundReturned() = runTest {
        // Arrange
        val mockInputPlayground = mockk<Playground>()
        coEvery { mockPlaygroundRepo.addPlayground(mockInputPlayground) } returns Result.success(
            mockPlayground
        )

        // Act
        val result = subject(mockInputPlayground).getOrThrow()

        // Assert
        assertEquals(mockPlayground, result)

        // Verify
        coVerify(exactly = 1) { mockPlaygroundRepo.addPlayground(mockInputPlayground) }
        coVerify(exactly = 1) { mockPlayground.equals(result) }
        confirmVerified(
            mockPlaygroundRepo,
            mockPlayground,
            mockInputPlayground
        )
    }
}