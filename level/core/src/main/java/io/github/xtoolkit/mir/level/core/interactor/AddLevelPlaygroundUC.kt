package io.github.xtoolkit.mir.level.core.interactor

import io.github.xtoolkit.mir.level.core.data.repository.LevelPlaygroundRepo
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.level.core.domain.LevelPlayground
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.util.core.BaseUseCase

class AddLevelPlaygroundUC(levelPlaygroundRepo: LevelPlaygroundRepo) :
    BaseUseCase<Pair<Level, Playground>, LevelPlayground, LevelPlaygroundRepo>(levelPlaygroundRepo) {
    override suspend fun execute(parameter: Pair<Level, Playground>) =
        repo.addLevelPlayground(parameter.first, parameter.second)
}