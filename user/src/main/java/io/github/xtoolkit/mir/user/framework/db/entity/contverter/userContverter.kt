package io.github.xtoolkit.mir.user.framework.db.entity.contverter

import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.user.framework.db.entity.UserEntity

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    name = name
)

fun UserEntity.toDomain(): User = User(
    id = id,
    name = name
)