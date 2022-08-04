package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.ui.deviceslist.DeviceslistViewModel
import com.onix.internship.okucherenko.ui.main.MainViewModel
import com.onix.internship.okucherenko.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DeviceslistViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }

}