package io.github.xtoolkit.mir.playground.presentation.mirza.characters

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class CharactersBoxTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenThreeChars_whenCharacterBoxCalled_andMoveTouchOnChars_thenChainCharsReturn() {
        // Arrange
        val characters = listOf('A', 'B', 'C')
        var composeSelected: String? = null

        // Act
        composeTestRule.setContent {
            CharactersBox(characters) { composeSelected = it }
        }
        val root = composeTestRule.onRoot()
        val items = characters.map { composeTestRule.onNodeWithText(it.toString()) }
        root.performTouchInput { down(center) }
        items.forEach { it.performTouchInput { moveTo(center) } }
        root.performTouchInput { up() }

        // Assert
        composeTestRule.waitForIdle()
        assertEquals(composeSelected, characters.joinToString(""))
    }

    @Test
    fun givenThreeChars_whenCharacterBoxCalled_andReset_andMoveTouchOnChars_thenChainCharsReturn() {
        // Arrange
        val characters = listOf('A', 'B', 'C')
        var composeSelected: String? = null
        var reset = {}

        // Act
        composeTestRule.setContent {
            reset = CharactersBox(characters) { composeSelected = it }
        }
        val root = composeTestRule.onRoot()
        val items = characters.map { composeTestRule.onNodeWithText(it.toString()) }
        root.performTouchInput { down(center) }
        items.forEach { it.performTouchInput { moveTo(center) } }
        root.performTouchInput { up() }
        reset()

        // Assert
        composeTestRule.waitForIdle()
        assertEquals(composeSelected, characters.joinToString(""))
    }

    @Test
    fun givenThreeChars_whenCharacterBoxCalled_andMoveTouchOnCharsTwice_thenChainCharsReturn() {
        // Arrange
        val characters = listOf('A', 'B', 'C')
        var composeSelected: String? = null

        // Act
        composeTestRule.setContent {
            CharactersBox(characters) { composeSelected = it }
        }
        val root = composeTestRule.onRoot()
        val items = characters.map { composeTestRule.onNodeWithText(it.toString()) }
        root.performTouchInput { down(center) }
        repeat(2) { items.forEach { it.performTouchInput { moveTo(center) } } }
        root.performTouchInput { up() }

        // Assert
        composeTestRule.waitForIdle()
        assertEquals(composeSelected, characters.joinToString(""))
    }

    @Test
    fun givenThreeChars_whenCharacterBoxCalled_andMoveTouchOnChars_andMoveEnd_thenChainCharsReturn() {
        // Arrange
        val characters = listOf('A', 'B', 'C')
        var composeSelected: String? = null

        // Act
        composeTestRule.setContent {
            CharactersBox(characters) { composeSelected = it }
        }
        val root = composeTestRule.onRoot()
        val items = characters.map { composeTestRule.onNodeWithText(it.toString()) }
        root.performTouchInput { down(center) }
        items.forEach { it.performTouchInput { moveTo(center) } }
        root.performTouchInput {
            moveTo(Offset(1F, 1F))
            moveTo(Offset(1000F, 1000F))
            up()
        }

        // Assert
        composeTestRule.waitForIdle()
        assertEquals(composeSelected, characters.joinToString(""))
    }
}