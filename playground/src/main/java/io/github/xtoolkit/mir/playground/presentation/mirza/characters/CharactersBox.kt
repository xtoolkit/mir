package io.github.xtoolkit.mir.playground.presentation.mirza.characters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private fun createCharacterItems(
    characters: List<Char>,
    boxSize: Dp,
    itemSize: Dp,
): List<CharactersItem> {
    val boxFloatSize = boxSize.value
    val itemSizeSize = itemSize.value
    val middle = (boxFloatSize / 2) - (itemSizeSize / 2)
    val ratio = boxFloatSize / characters.size

    return characters.shuffled().mapIndexed { i, c ->
        val itemRatio = ratio * i
        val angle = itemRatio / (boxFloatSize / 2) * PI
        val x = middle * sin(angle)
        val y = middle * -cos(angle)

        CharactersItem(
            label = c.toString(),
            xPosition = (middle + x).dp,
            yPosition = (middle + y).dp,
        )
    }.toMutableStateList()
}

@Composable
fun CharactersBox(
    characters: List<Char>,
    boxPadding: Dp = 10.dp,
    boxSize: Dp = 300.dp,
    itemSize: Dp = boxSize / 5,
    borderColor: Color = MaterialTheme.colorScheme.tertiary,
    onChange: (String) -> Unit = {},
    onSelect: (String) -> Unit,
): () -> Unit {
    val items = remember {
        mutableStateListOf<CharactersItem>().apply {
            addAll(
                createCharacterItems(
                    characters = characters,
                    boxSize = boxSize,
                    itemSize = itemSize
                )
            )
        }
    }
    val drawChain = remember { mutableStateListOf<Pair<Float, Float>>() }
    val selectedItem = remember { mutableStateListOf<Int>() }
    var lastTouchPosition by remember { mutableStateOf(0F to 0F) }
    val getSelectedItemValue = { selectedItem.joinToString("") { items[it].label } }
    val end = {
        onSelect(getSelectedItemValue())
        selectedItem.clear()
        drawChain.clear()
    }

    Box(
        modifier = Modifier
            .requiredSize((boxPadding * 2) + boxSize)
            .clip(RoundedCornerShape(boxSize))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(boxPadding)
    ) {
        items.forEach {
            Row(
                modifier = Modifier
                    .offset(x = it.xPosition, y = it.yPosition)
                    .size(itemSize)
                    .clip(RoundedCornerShape(itemSize))
                    .background(MaterialTheme.colorScheme.onSurfaceVariant),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) { Text(text = it.label, color = MaterialTheme.colorScheme.surfaceVariant) }
        }

        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = end,
                    onDragCancel = end
                ) { change, _ ->
                    val p = change.position
                    lastTouchPosition = p.x to p.y
                    items
                        .forEachIndexed { i, item ->
                            val size = itemSize.toPx()
                            val x = item.xPosition.toPx()
                            val y = item.yPosition.toPx()
                            if (
                                p.x > x && p.x < (x + size) &&
                                p.y > y && p.y < (y + size) &&
                                selectedItem.find { s -> s == i } == null
                            ) {
                                selectedItem.add(i)
                                onChange(getSelectedItemValue())
                                drawChain.add((x + (size / 2)) to (y + (size / 2)))
                            }
                        }
                }
            }) {
            drawChain.forEachIndexed { i, item ->
                val endPosition = if (drawChain.size - 1 == i)
                    Offset(lastTouchPosition.first, lastTouchPosition.second)
                else {
                    val next = drawChain[i + 1]
                    Offset(next.first, next.second)
                }
                drawLine(
                    color = borderColor,
                    start = Offset(item.first, item.second),
                    end = endPosition,
                    strokeWidth = 10F
                )
            }
        }
    }

    return {
        items.clear()
        items.addAll(
            createCharacterItems(
                characters = characters,
                boxSize = boxSize,
                itemSize = itemSize
            )
        )
        val x = items
    }
}