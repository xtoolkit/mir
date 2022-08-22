package io.github.xtoolkit.mir.playground.presentation.mirza.converter

import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.playground.presentation.mirza.MirzaPlaygroundEntity

fun Playground.toMirzaPlaygroundEntity(): MirzaPlaygroundEntity {
    val sortedWords = words.sortedBy { it.length }
    return MirzaPlaygroundEntity(
        id = id,
        characters = sortedWords.last().toList().sorted(),
        words = sortedWords
    )
}