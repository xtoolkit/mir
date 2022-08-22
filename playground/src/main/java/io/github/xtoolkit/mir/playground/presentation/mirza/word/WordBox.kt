package io.github.xtoolkit.mir.playground.presentation.mirza.word

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WordBox(word: String, borderRadios: Dp = 8.dp) =
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        word.forEach {
            val isEmpty = it == ' '
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .requiredSize(38.dp)
                    .clip(RoundedCornerShape(borderRadios))
                    .background(
                        if (isEmpty) MaterialTheme.colorScheme.tertiaryContainer
                        else MaterialTheme.colorScheme.secondary
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(borderRadios)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = it.toString(),
                    color = if (isEmpty) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }