package io.github.xtoolkit.mir.util.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import com.google.android.material.composethemeadapter3.createMdc3Theme

private val LightThemeColors = lightColorScheme()

private val DarkThemeColors = darkColorScheme()

private val ThemeTypography = Typography()

private val ThemeShapes = Shapes()

@Composable
fun DefaultTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val appColor = if (darkTheme) DarkThemeColors else LightThemeColors

    val (colorScheme, typography, shapes) = createMdc3Theme(
        context = LocalContext.current,
        layoutDirection = LocalLayoutDirection.current
    )

    MaterialTheme(
        colorScheme = colorScheme ?: appColor,
        typography = typography ?: ThemeTypography,
        shapes = shapes ?: ThemeShapes,
        content = content
    )
}