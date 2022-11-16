package io.github.xtoolkit.mir.playground.core.interactor

import io.github.xtoolkit.mir.playground.core.data.repository.PlaygroundRepo
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class GetPlaygroundByIdUCTest {
    private lateinit var subject: GetPlaygroundByIdUC

    @MockK
    private lateinit var mockPlayground: Playground

    @MockK
    private lateinit var mockPlaygroundRepo: PlaygroundRepo

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        subject = GetPlaygroundByIdUC(mockPlaygroundRepo)
        every { mockPlayground.id } returns 7
    }

    @After
    fun tearDown() {
        unmockkAll()
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
            assert(playgroundId == result.id)

            // Verify
            coVerify(exactly = 1) { mockPlaygroundRepo.getPlaygroundByID(playgroundId) }
            coVerify(exactly = 1) { mockPlayground.id }
            confirmVerified(
                mockPlayground,
                mockPlaygroundRepo
            )
        }
}