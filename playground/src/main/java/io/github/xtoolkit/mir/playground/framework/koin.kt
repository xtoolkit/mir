package io.github.xtoolkit.mir.playground.framework

import io.github.xtoolkit.mir.playground.framework.di.playgroundRepositoriesModule
import io.github.xtoolkit.mir.playground.framework.di.playgroundUseCasesModule

val playgroundKoinModules = arrayOf(
    playgroundRepositoriesModule,
    playgroundUseCasesModule
)