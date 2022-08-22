package io.github.xtoolkit.mir.presentation.app

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.github.xtoolkit.mir.level.presentation.multi.MultiLevelPresentation
import io.github.xtoolkit.mir.user.presentation.start.StartPresentation

@Composable
fun AppPresentation() {
    var state by rememberSaveable { mutableStateOf("start") }

    BackHandler(enabled = state != "start") {
        state = "start"
    }

    Crossfade(targetState = state) {
        when (it) {
            "start" -> StartPresentation() { state = "level" }
            "level" -> MultiLevelPresentation()
        }
    }
}