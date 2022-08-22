package io.github.xtoolkit.mir.playground.core.interactor

import io.github.xtoolkit.mir.playground.core.data.repository.PlaygroundRepo
import io.github.xtoolkit.mir.playground.core.domain.Playground
import io.github.xtoolkit.mir.util.core.BaseUseCase

class AddPlaygroundUC(playgroundRepo: PlaygroundRepo) :
    BaseUseCase<Playground, Playground, PlaygroundRepo>(playgroundRepo) {
    override suspend fun execute(parameter: Playground) = repo.addPlayground(parameter)
}