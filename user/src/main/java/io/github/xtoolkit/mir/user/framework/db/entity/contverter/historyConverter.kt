package io.github.xtoolkit.mir.user.framework.db.entity.contverter

import io.github.xtoolkit.mir.user.core.domain.History
import io.github.xtoolkit.mir.user.framework.db.entity.HistoryEntity

fun History.toEntity(): HistoryEntity = HistoryEntity(
    id = id,
    userId = userId,
    playgroundId = playgroundId,
    found = found.joinToString(","),
    done = done
)

fun HistoryEntity.toDomain(): History = History(
    id = id,
    userId = userId,
    playgroundId = playgroundId,
    found = found.split(","),
    done = done
)