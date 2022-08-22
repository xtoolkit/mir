package io.github.xtoolkit.mir.level.framework.di

import io.github.xtoolkit.mir.level.presentation.mirza.LevelMirzaViewModel
import io.github.xtoolkit.mir.level.presentation.multi.MultiLevelViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val levelViewModelModule = module {
    viewModelOf(::MultiLevelViewModel)
    viewModelOf(::LevelMirzaViewModel)
}