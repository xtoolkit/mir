package io.github.xtoolkit.mir.playground.framework

import io.github.xtoolkit.mir.playground.framework.db.dao.PlaygroundDao
import io.mockk.*
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule

class KoinTest: KoinTest {
    private val needModule = module {
        single { mockk<PlaygroundDao>() }
    }

    @get:Rule
    val mockProvider = MockProviderRule.create {
        mockkClass(it)
    }

    @After
    fun setDown() {
        unmockkAll()
    }

    @Test
    fun checkPlaygroundKoinModules() {
        checkModules {
            modules(needModule, *playgroundKoinModules)
        }
    }
}