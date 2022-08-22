package io.github.xtoolkit.mir.user.framework.di

import io.github.xtoolkit.mir.user.presentation.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val userViewModelModule = module {
    viewModelOf(::StartViewModel)
}