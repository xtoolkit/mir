package io.github.xtoolkit.mir.util.presentation.loading

import android.graphics.Paint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

data class LoadingItem(
    val char: Char,
    val size: Animatable<Float, AnimationVector1D>,
    val alpha: Animatable<Float, AnimationVector1D>,
    val x: Animatable<Float, AnimationVector1D>,
    val y: Animatable<Float, AnimationVector1D>,
    val rotate: Animatable<Float, AnimationVector1D>
)

fun getRandomAlphabet(length: Int): List<Char> {
    val chars = ('A'..'Z') + ('a'..'z')
    return (1..length).map { chars.random() }
}

fun getLoadingItems(length: Int) = getRandomAlphabet(length).map {
    LoadingItem(
        char = it,
        size = Animatable(initialValue = (20..200).random().toFloat()),
        alpha = Animatable(initialValue = Random.nextFloat()),
        x = Animatable(initialValue = Random.nextFloat()),
        y = Animatable(initialValue = Random.nextFloat()),
        rotate = Animatable(initialValue = (0..359).random().toFloat()),
    )
}

@Composable
fun DefaultLoading(
    length: Int = 100,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    baseTextSize: Float = 1.sp.value
) {
    val items = remember {
        mutableStateListOf<LoadingItem>().apply { addAll(getLoadingItems((length))) }
    }

    items.forEach {
        LaunchedEffect(it.rotate) {
            withContext(Dispatchers.Unconfined) {
                val initial = it.rotate.value
                val target = (0..359).random().toFloat()
                val duration = (100..500).random() * 150

                it.rotate.animateTo(
                    targetValue = target,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = duration
                            initial at 0 with LinearOutSlowInEasing
                            target at duration / 2 with LinearOutSlowInEasing
                            initial at duration with LinearOutSlowInEasing
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }

        LaunchedEffect(it.x) {
            withContext(Dispatchers.Unconfined) {
                val initial = it.x.value
                val target = Random.nextFloat()
                val duration = (100..500).random() * 150

                it.x.animateTo(
                    targetValue = target,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = duration
                            initial at 0 with LinearOutSlowInEasing
                            target at duration / 2 with LinearOutSlowInEasing
                            initial at duration with LinearOutSlowInEasing
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }

        LaunchedEffect(it.y) {
            withContext(Dispatchers.Unconfined) {
                val initial = it.y.value
                val target = Random.nextFloat()
                val duration = (100..500).random() * 150

                it.y.animateTo(
                    targetValue = target,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = duration
                            initial at 0 with LinearOutSlowInEasing
                            target at (0..duration).random() with LinearOutSlowInEasing
                            initial at duration with LinearOutSlowInEasing
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        items.forEach {
            val left = it.x.value * size.width
            val top = it.y.value * size.height
            withTransform({
                rotate(it.rotate.value, Offset(left, top))
                translate(left, top)
            }) {
                drawContext.canvas.nativeCanvas.drawText(
                    it.char.toString(),
                    0F,
                    0F,
                    Paint().apply {
                        textSize = baseTextSize * it.size.value
                        color = textColor.copy(alpha = it.alpha.value).toArgb()
                    }
                )
            }
        }
    }
}