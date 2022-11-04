package io.github.xtoolkit.mir.playground.presentation.mirza.word

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.google.android.material.R.style.Theme_Material3_DynamicColors_DayNight
import io.github.xtoolkit.mir.util.presentation.layout.DefaultLayout
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WordBoxTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.activity.setTheme(Theme_Material3_DynamicColors_DayNight)
    }

    @Test
    fun givenWord_whenUseWordBox_thenShowSplitWord() {
        // Arrange
        val word = "Hello"

        // Act
        composeTestRule.setContent {
            DefaultLayout {
                WordBox(word)
            }
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
            DefaultLayout {
                WordBox("     ")
            }
        }

        // Assert
        word.forEach {
            composeTestRule.onNodeWithText(it.toString()).assertDoesNotExist()
        }
    }
}