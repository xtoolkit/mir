package io.github.xtoolkit.mir.level.presentation.single

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.util.presentation.layout.DefaultLayout

@Composable
fun SingleLevelPresentation(
    level: Level,
    playgrounds: List<Pair<Int, Boolean>>,
    onSelect: (Int) -> Unit
) = DefaultLayout {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) { Text(text = level.title, color = MaterialTheme.colorScheme.onBackground) }

        LazyVerticalGrid(
            modifier = Modifier.weight(1F),
            columns = GridCells.Adaptive(70.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(playgrounds.size) { i ->
                val item = playgrounds[i]
                val isEdge = item.second && !(playgrounds.getOrNull(i + 1)?.second ?: true)
                FilledTonalButton(
                    modifier = Modifier.padding(3.dp).aspectRatio(1.0F),
                    onClick = { onSelect(item.first) },
                    colors = if (isEdge) ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary,
                    ) else ButtonDefaults.filledTonalButtonColors(),
                    enabled = item.second
                ) {
                    if (item.second) Text(text = (i + 1).toString())
                    else Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock")
                }
            }
        }
    }
}