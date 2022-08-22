package io.github.xtoolkit.mir.util.presentation.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.xtoolkit.mir.util.presentation.theme.DefaultTheme

@Composable
fun DefaultLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()

    DefaultTheme {
        val background = MaterialTheme.colorScheme.background

        SideEffect {
            systemUiController.setSystemBarsColor(color = background)
            systemUiController.setNavigationBarColor(color = background)
        }

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(background)
        ) { content() }
    }
}