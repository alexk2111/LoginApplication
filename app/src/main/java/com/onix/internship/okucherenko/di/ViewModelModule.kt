package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.ui.home.HomeViewModel
import com.onix.internship.okucherenko.ui.location.LocationViewModel
import com.onix.internship.okucherenko.ui.main.MainViewModel
import com.onix.internship.okucherenko.ui.settings.SettingsViewModel
import com.onix.internship.okucherenko.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SettingsViewModel() }
    viewModel { LocationViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }

}