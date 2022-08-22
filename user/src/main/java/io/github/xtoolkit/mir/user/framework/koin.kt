package io.github.xtoolkit.mir.user.framework

import io.github.xtoolkit.mir.user.framework.di.userRepositoriesModule
import io.github.xtoolkit.mir.user.framework.di.userUseCasesModule
import io.github.xtoolkit.mir.user.framework.di.userViewModelModule

val userKoinModules = arrayOf(
    userRepositoriesModule,
    userUseCasesModule,
    userViewModelModule
)