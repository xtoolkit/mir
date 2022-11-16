package io.github.xtoolkit.mir.playground.framework.db.ds

import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.playground.framework.db.dao.PlaygroundDao
import io.github.xtoolkit.mir.playground.framework.db.entity.PlaygroundEntity
import io.github.xtoolkit.mir.playground.framework.db.entity.converter.toDomain
import io.github.xtoolkit.mir.playground.framework.db.entity.converter.toEntity
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlaygroundLocalDSTest {
    private lateinit var subject: PlaygroundLocalDS

    private val playgroundFakeId = 7

    private lateinit var playgroundEntity: PlaygroundEntity

    @MockK
    private lateinit var mockPlaygroundDao: PlaygroundDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        subject = PlaygroundLocalDS(mockPlaygroundDao)
        playgroundEntity = PlaygroundEntity(playgroundFakeId, "")
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenPlaygroundId_whenPlaygroundLocalDSGetByIdCalled_thenDSPlaygroundMatchReturned() =
        runTest {
            // Arrange
            coEvery { mockPlaygroundDao.getById(playgroundFakeId) } returns playgroundEntity

            // Act
            val result = subject.getById(playgroundFakeId).getOrThrow()

            // Assert
            assertEquals(playgroundFakeId, result.id)

            // Verify
            coVerify(exactly = 1) { mockPlaygroundDao.getById(playgroundFakeId) }
            confirmVerified(mockPlaygroundDao)
        }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenPlayground_whenPlaygroundLocalDSADDCalled_thenDSPlaygroundReturned() = runTest {
        // Arrange
        val inputPlayground = Playground(0, listOf())
        val inputPlaygroundEntity = inputPlayground.toEntity()
        coEvery { mockPlaygroundDao.getById(playgroundFakeId) } returns playgroundEntity
        coEvery { mockPlaygroundDao.add(inputPlaygroundEntity) } returns playgroundFakeId.toLong()

        // Act
        val result = subject.add(inputPlayground).getOrThrow()

        // Assert
        assertEquals(playgroundFakeId, result.id)

        // Verify
        coVerify(exactly = 1) { mockPlaygroundDao.getById(playgroundFakeId) }
        coVerify(exactly = 1) { mockPlaygroundDao.add(inputPlaygroundEntity) }
        confirmVerified(mockPlaygroundDao)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenBadPlayground_whenPlaygroundLocalDSADDCalled_thenFailResultReturned() = runTest {
        // Arrange
        val exception = Exception("DS problem")
        val inputPlaygroundEntity = PlaygroundEntity(-1, "")
        coEvery { mockPlaygroundDao.add(inputPlaygroundEntity) }.throws(exception)

        // Act
        val result = subject.add(inputPlaygroundEntity.toDomain())

        // Assert
        result.onFailure {
            assertEquals(exception.message, it.message)
        }

        // Verify
        coVerify(exactly = 1) { mockPlaygroundDao.add(inputPlaygroundEntity) }
        confirmVerified(mockPlaygroundDao)
    }
}