package io.github.xtoolkit.mir.playground.presentation.mirza.converter

import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.playground.presentation.mirza.MirzaPlaygroundEntity

fun Playground.toMirzaPlaygroundEntity(): MirzaPlaygroundEntity {
    val chars = mutableListOf<Char>()
    words.forEach { word ->
        word.forEach { char ->
            if (chars.find { it == char } == null) chars.add(char)
        }
    }
    return MirzaPlaygroundEntity(
        id = id,
        characters = chars,
        words = words.sortedBy { it.length }
    )
}