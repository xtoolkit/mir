package io.github.xtoolkit.mir.playground.presentation.mirza

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.xtoolkit.mir.playground.presentation.mirza.characters.CharactersBox
import io.github.xtoolkit.mir.playground.presentation.mirza.word.WordBox
import io.github.xtoolkit.mir.util.presentation.layout.DefaultLayout

@Composable
fun MirzaPlaygroundPresentation(
    mirzaPlaygroundEntity: MirzaPlaygroundEntity,
    found: MutableList<String>,
    emit: (List<String>) -> Unit
) = DefaultLayout {
    var title by rememberSaveable { mutableStateOf("Mir say: Hello Noobe Sag!") }
    var shuffleChars = {}

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) { Surface { Text(text = title) } }

        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
        ) {
            mirzaPlaygroundEntity.words.forEach {
                WordBox(
                    word = if (found.find { x -> x == it } == null) " ".repeat(it.length) else it
                )
            }
        }

        Row(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            shuffleChars = CharactersBox(
                characters = mirzaPlaygroundEntity.characters,
                onChange = { title = it }
            ) {
                if (mirzaPlaygroundEntity.words.find { x -> x == it } == null)
                    title = "Wrong word!"
                else {
                    if (found.find { x -> x == it } == null) {
                        found.add(it)
                        title = "Yes!"
                        emit(found)
                    } else {
                        title = "Duplicated!"
                    }
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = shuffleChars) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Shuffle")
            }
        }
    }
}