package io.github.xtoolkit.mir.level.framework.di

import io.github.xtoolkit.mir.level.core.data.repository.LevelPlaygroundRepo
import io.github.xtoolkit.mir.level.core.data.repository.LevelRepo
import io.github.xtoolkit.mir.level.framework.db.ds.LevelLocalDS
import io.github.xtoolkit.mir.level.framework.db.ds.LevelPlaygroundLocalDS
import org.koin.dsl.module

val levelRepositoriesModule = module {
    single { LevelLocalDS(get()) }
    single { LevelRepo(get<LevelLocalDS>()) }

    single { LevelPlaygroundLocalDS(get()) }
    single { LevelPlaygroundRepo(get<LevelPlaygroundLocalDS>()) }
}