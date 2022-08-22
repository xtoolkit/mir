package io.github.xtoolkit.mir.level.framework.di

import io.github.xtoolkit.mir.level.core.interactor.AddLevelPlaygroundUC
import io.github.xtoolkit.mir.level.core.interactor.AddLevelUC
import io.github.xtoolkit.mir.level.core.interactor.GetLevelPlaygroundUC
import io.github.xtoolkit.mir.level.core.interactor.GetLevelsUC
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val levelUseCasesModule = module {
    singleOf(::AddLevelUC)
    singleOf(::GetLevelsUC)

    singleOf(::AddLevelPlaygroundUC)
    singleOf(::GetLevelPlaygroundUC)
}