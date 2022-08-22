package io.github.xtoolkit.mir.level.core.interactor

import io.github.xtoolkit.mir.level.core.data.repository.LevelPlaygroundRepo
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.level.core.domain.LevelPlayground
import io.github.xtoolkit.mir.util.core.BaseUseCase

class GetLevelPlaygroundUC(levelPlaygroundRepo: LevelPlaygroundRepo) :
    BaseUseCase<Level, List<LevelPlayground>, LevelPlaygroundRepo>(levelPlaygroundRepo) {
    override suspend fun execute(parameter: Level) = repo.getLevelPlayground(parameter)
}