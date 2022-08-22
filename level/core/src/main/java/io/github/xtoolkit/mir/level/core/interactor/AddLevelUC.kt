package io.github.xtoolkit.mir.level.core.interactor

import io.github.xtoolkit.mir.level.core.data.repository.LevelRepo
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.util.core.BaseUseCase

class AddLevelUC(levelRepo: LevelRepo) : BaseUseCase<Level, Level, LevelRepo>(levelRepo) {
    override suspend fun execute(parameter: Level) = repo.addLevel(parameter)
}