package io.github.xtoolkit.mir.level.framework.db.entity.converter

import io.github.xtoolkit.mir.level.core.domain.LevelPlayground
import io.github.xtoolkit.mir.level.framework.db.entity.LevelPlaygroundEntity

fun LevelPlayground.toEntity(): LevelPlaygroundEntity = LevelPlaygroundEntity(
    id = id,
    levelId = levelId,
    playgroundId = playgroundId
)

fun LevelPlaygroundEntity.toDomain(): LevelPlayground = LevelPlayground(
    id = id,
    levelId = levelId,
    playgroundId = playgroundId
)