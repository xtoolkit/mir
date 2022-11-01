package io.github.xtoolkit.mir.playground.core.interactor

import io.github.xtoolkit.mir.playground.core.data.repository.PlaygroundRepo
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class AddPlaygroundUCTest {
    private lateinit var subject: AddPlaygroundUC

    @MockK
    private lateinit var mockPlayground: Playground

    @MockK
    private lateinit var mockPlaygroundRepo: PlaygroundRepo

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        subject = AddPlaygroundUC(mockPlaygroundRepo)
    }

    @After
    fun tearDown() {
        unmockkAll()
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