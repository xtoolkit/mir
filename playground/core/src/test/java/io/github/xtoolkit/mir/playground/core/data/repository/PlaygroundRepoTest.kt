package io.github.xtoolkit.mir.playground.core.data.repository

import io.github.xtoolkit.mir.playground.core.data.datasource.PlaygroundDS
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlaygroundRepoTest {
    private lateinit var subject: PlaygroundRepo

    @MockK
    private lateinit var mockPlaygroundDS: PlaygroundDS

    @MockK
    private lateinit var mockPlayground: Playground

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        subject = PlaygroundRepo(mockPlaygroundDS)
        every { mockPlayground.id } returns 7
    }

    @After
    fun setDown() {
        unmockkAll()
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenPlaygroundCandidate_whenPlaygroundRepoAddPlaygroundCalled_thenNewPlaygroundReturned() =
        runTest {
            // Arrange
            val mockInputPlayground = mockk<Playground>()
            coEvery { mockPlaygroundDS.add(mockInputPlayground) } returns Result.success(
                mockPlayground
            )

            // Act
            val result = subject.addPlayground(mockInputPlayground).getOrThrow()

            // Assert
            assertEquals(mockPlayground, result)

            // Verify
            coVerify(exactly = 1) { mockPlaygroundDS.add(mockInputPlayground) }
            coVerify(exactly = 1) { mockPlayground.equals(result) }
            confirmVerified(
                mockPlaygroundDS,
                mockPlayground,
                mockInputPlayground
            )
        }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenPlaygroundId_whenGetPlaygroundByIdUCInvoked_thenPlaygroundReturnedWithMatchId() =
        runTest {
            // Arrange
            val playgroundId = 7
            coEvery { mockPlaygroundDS.getById(playgroundId) } returns Result.success(
                mockPlayground
            )

            // Act
            val result = subject.getPlaygroundByID(playgroundId).getOrThrow()

            // Assert
            assertEquals(playgroundId, result.id)

            // Verify
            coVerify(exactly = 1) { mockPlaygroundDS.getById(playgroundId) }
            coVerify(exactly = 1) { mockPlayground.id }
            confirmVerified(
                mockPlayground,
                mockPlaygroundDS
            )
        }
}