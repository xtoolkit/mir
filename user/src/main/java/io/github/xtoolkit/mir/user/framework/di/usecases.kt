package io.github.xtoolkit.mir.user.framework.di

import io.github.xtoolkit.mir.user.core.interactor.GetCurrentUserUC
import io.github.xtoolkit.mir.user.core.interactor.GetUserHistoryUC
import io.github.xtoolkit.mir.user.core.interactor.SyncHistoryUC
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val userUseCasesModule = module {
    singleOf(::GetCurrentUserUC)

    singleOf(::SyncHistoryUC)
    singleOf(::GetUserHistoryUC)
}

