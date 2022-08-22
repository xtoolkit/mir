package io.github.xtoolkit.mir.playground.framework.di

import io.github.xtoolkit.mir.playground.core.data.repository.PlaygroundRepo
import io.github.xtoolkit.mir.playground.framework.db.ds.PlaygroundLocalDS
import org.koin.dsl.module

val playgroundRepositoriesModule = module {
    single { PlaygroundLocalDS(get()) }
    single { PlaygroundRepo(get<PlaygroundLocalDS>()) }
}