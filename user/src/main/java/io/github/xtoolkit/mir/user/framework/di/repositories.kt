package io.github.xtoolkit.mir.user.framework.di

import io.github.xtoolkit.mir.user.core.data.repository.HistoryRepo
import io.github.xtoolkit.mir.user.core.data.repository.UserRepo
import io.github.xtoolkit.mir.user.framework.db.ds.HistoryLocalDS
import io.github.xtoolkit.mir.user.framework.db.ds.UserLocalDS
import org.koin.dsl.module

val userRepositoriesModule = module {
    single { UserLocalDS(get()) }
    single { UserRepo(get<UserLocalDS>()) }

    single { HistoryLocalDS(get()) }
    single { HistoryRepo(get<HistoryLocalDS>()) }
}