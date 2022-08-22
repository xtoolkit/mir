package io.github.xtoolkit.mir.level.presentation.mirza

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.xtoolkit.mir.playground.presentation.mirza.MirzaPlaygroundPresentation
import io.github.xtoolkit.mir.playground.presentation.mirza.converter.toMirzaPlaygroundEntity
import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.util.presentation.layout.DefaultLayout
import org.koin.androidx.compose.getViewModel

@Composable
fun LevelMirzaPresentation(
    playgroundId: Int,
    user: User,
    viewModel: LevelMirzaViewModel = getViewModel(),
    back: () -> Unit
) = DefaultLayout(modifier = Modifier.padding(30.dp)) {
    SideEffect { viewModel.start(user, playgroundId) }

    var endModal by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        viewModel.playground?.let {
            if (playgroundId == it.id) MirzaPlaygroundPresentation(
                it.toMirzaPlaygroundEntity(),
                viewModel.found
            ) { list ->
                viewModel.updateHistory()
                if (list.size == it.words.size) endModal = true
            }
        }

        if (endModal) AlertDialog(
            title = { Text(text = "Congregation!") },
            text = { Text(text = "${user.name} you can play next level!") },
            onDismissRequest = { endModal = false },
            dismissButton = {
                TextButton(onClick = { endModal = false }) { Text(text = "Cancel") }
            },
            confirmButton = {
                TextButton(onClick = { endModal = false; back() }) { Text(text = "Back") }
            }
        )
    }
}
