package io.github.xtoolkit.mir.user.presentation.start

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.xtoolkit.mir.util.presentation.layout.DefaultLayout
import org.koin.androidx.compose.getViewModel

@Composable
fun StartPresentation(
    viewModel: StartViewModel = getViewModel(),
    onSelect: () -> Unit
) = DefaultLayout(modifier = Modifier.padding(20.dp)) {
    SideEffect { viewModel.start() }

    if (viewModel.user != null) Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hello ${viewModel.user!!.name}!",
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Row(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier.requiredSize(100.dp),
                onClick = onSelect
            ) {
                Text(text = "Start")
            }
        }
    } else Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) { CircularProgressIndicator() }
}