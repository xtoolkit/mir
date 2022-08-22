package io.github.xtoolkit.mir.user.core.interactor

import io.github.xtoolkit.mir.user.core.data.repository.HistoryRepo
import io.github.xtoolkit.mir.user.core.domain.History
import io.github.xtoolkit.mir.util.core.BaseUseCase

class SyncHistoryUC(historyRepo: HistoryRepo) :
    BaseUseCase<History, History, HistoryRepo>(historyRepo) {
    override suspend fun execute(parameter: History) = repo.syncHistory(parameter)
}