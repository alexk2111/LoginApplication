package com.onix.internship.di

import com.onix.internship.ui.game.GameViewModel
import com.onix.internship.ui.main.MainViewModel
import com.onix.internship.ui.splash.SplashViewModel
import com.onix.internship.ui.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GameViewModel(firstStep = get(), userChip = get()) }
    viewModel { StartViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }

}