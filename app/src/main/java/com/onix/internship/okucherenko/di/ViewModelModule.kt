package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.ui.main.MainViewModel
import com.onix.internship.okucherenko.ui.splash.SplashViewModel
import com.onix.internship.okucherenko.ui.wifimanager.ManagerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ManagerViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }

}