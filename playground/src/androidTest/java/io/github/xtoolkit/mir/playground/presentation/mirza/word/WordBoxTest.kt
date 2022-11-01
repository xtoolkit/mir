package io.github.xtoolkit.mir.playground.presentation.mirza.word

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class WordBoxTest {
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
        composeTestRule.onRoot().onChildren().apply {
            assertCountEquals(word.length)

            fetchSemanticsNodes().forEachIndexed { i, _ ->
                get(i).assertTextEquals(word[i].toString())
            }
        }
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
        word.forEach {
            composeTestRule.onNodeWithText(it.toString()).assertDoesNotExist()
        }
    }
}