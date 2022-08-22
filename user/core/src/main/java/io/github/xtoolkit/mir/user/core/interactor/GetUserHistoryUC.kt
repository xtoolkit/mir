package io.github.xtoolkit.mir.user.core.interactor

import io.github.xtoolkit.mir.user.core.data.repository.HistoryRepo
import io.github.xtoolkit.mir.user.core.domain.History
import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.util.core.BaseUseCase

class GetUserHistoryUC(historyRepo: HistoryRepo) :
    BaseUseCase<User, List<History>, HistoryRepo>(historyRepo) {
    override suspend fun execute(parameter: User) = repo.getUserHistory(parameter)
}