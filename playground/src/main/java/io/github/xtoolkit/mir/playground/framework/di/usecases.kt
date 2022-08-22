package io.github.xtoolkit.mir.playground.framework.di

import io.github.xtoolkit.mir.playground.core.interactor.AddPlaygroundUC
import io.github.xtoolkit.mir.playground.core.interactor.GetPlaygroundByIdUC
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val playgroundUseCasesModule = module {
    singleOf(::AddPlaygroundUC)
    singleOf(::GetPlaygroundByIdUC)
}