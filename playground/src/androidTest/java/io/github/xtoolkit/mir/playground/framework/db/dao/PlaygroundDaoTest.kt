package io.github.xtoolkit.mir.playground.framework.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.github.xtoolkit.mir.playground.framework.db.PlaygroundDatabase
import io.github.xtoolkit.mir.playground.framework.db.entity.PlaygroundEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PlaygroundDaoTest {
    private lateinit var playgroundDao: PlaygroundDao
    private lateinit var db: PlaygroundDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PlaygroundDatabase::class.java
        ).build()
        playgroundDao = db.playgroundDao()
    }

    @After
    @Throws(IOException::class)
    fun setDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenPlayground_whenPlaygroundDaoAddCalled_thenDBPlaygroundReturned() = runTest {
        // Arrange
        val playgroundEntity = PlaygroundEntity(0, "test")

        // Act
        val id = playgroundDao.add(playgroundEntity)

        // Assert
        assertNotEquals(0, id)
    }

    @Test
    @Throws(Exception::class)
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenNotExistPlaygroundId_whenPlaygroundDaoAddCalled_thenNullReturned() = runTest {
        // Arrange
        val playgroundEntityId = 7

        // Act
        val playgroundEntity = playgroundDao.getById(playgroundEntityId)

        // Assert
        assertEquals(null, playgroundEntity)
    }

    @Test
    @Throws(Exception::class)
    @OptIn(ExperimentalCoroutinesApi::class)
    fun givenExistPlaygroundId_whenPlaygroundDaoAddCalled_thenNullReturned() = runTest {
        // Arrange
        val playgroundEntityId = 7
        playgroundDao.add(PlaygroundEntity(playgroundEntityId, "test"))

        // Act
        val playgroundEntity = playgroundDao.getById(playgroundEntityId)

        // Assert
        assertEquals(playgroundEntityId, playgroundEntity?.id)
    }
}