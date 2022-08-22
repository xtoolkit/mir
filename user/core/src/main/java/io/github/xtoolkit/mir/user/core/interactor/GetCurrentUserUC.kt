package io.github.xtoolkit.mir.user.core.interactor

import io.github.xtoolkit.mir.user.core.data.repository.UserRepo
import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.util.core.BaseUseCase

class GetCurrentUserUC(userRepo: UserRepo) : BaseUseCase<Unit, User, UserRepo>(userRepo) {
    override suspend fun execute(parameter: Unit) = repo.getCurrentUser()
}