package io.github.xtoolkit.mir.playground.presentation.mirza.word

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class WordBoxKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenWord_whenUseWordBox_thenShowSplitWord() {
        // Arrange
        val word = "Hello"

        // Act
        composeTestRule.setContent {
            WordBox(word)
        }

        // Assert
        composeTestRule.onNodeWithText(word[0].toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(word[1].toString()).assertIsDisplayed()
        composeTestRule.onAllNodesWithText(word[2].toString()).onFirst().assertIsDisplayed()
        composeTestRule.onNodeWithText(word[4].toString()).assertIsDisplayed()
    }

    @Test
    fun givenEmptyWord_whenUseWordBox_thenShowEmptyBox() {
        // Arrange
        val word = "Hello"

        // Act
        composeTestRule.setContent {
            WordBox("     ")
        }

        // Assert
        composeTestRule.onNodeWithText(word[0].toString()).assertDoesNotExist()
        composeTestRule.onNodeWithText(word[1].toString()).assertDoesNotExist()
        composeTestRule.onAllNodesWithText(word[2].toString()).onFirst().assertDoesNotExist()
        composeTestRule.onNodeWithText(word[4].toString()).assertDoesNotExist()
    }
}