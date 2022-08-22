package io.github.xtoolkit.mir.playground.framework.db.entity.converter

import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.playground.framework.db.entity.PlaygroundEntity

fun Playground.toEntity(): PlaygroundEntity = PlaygroundEntity(
    id = id,
    words = words.joinToString(",")
)

fun PlaygroundEntity.toDomain(): Playground = Playground(
    id = id,
    words = words.split(",")
)