package io.github.xtoolkit.mir.level.framework.db.entity.converter

import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.level.framework.db.entity.LevelEntity

fun Level.toEntity(): LevelEntity = LevelEntity(
    id = id,
    title = title
)

fun LevelEntity.toDomain(): Level = Level(
    id = id,
    title = title,
)