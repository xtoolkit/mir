package io.github.xtoolkit.mir.level.framework

import io.github.xtoolkit.mir.level.framework.di.levelRepositoriesModule
import io.github.xtoolkit.mir.level.framework.di.levelUseCasesModule
import io.github.xtoolkit.mir.level.framework.di.levelViewModelModule

val levelKoinModules = arrayOf(
    levelRepositoriesModule,
    levelUseCasesModule,
    levelViewModelModule
)