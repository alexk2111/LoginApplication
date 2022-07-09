package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.ui.black.BlackViewModel
import com.onix.internship.okucherenko.ui.club.ClubViewModel
import com.onix.internship.okucherenko.ui.lecturehall.LecturehallViewModel
import com.onix.internship.okucherenko.ui.main.MainViewModel
import com.onix.internship.okucherenko.ui.meadow.MeadowViewModel
import com.onix.internship.okucherenko.ui.splash.SplashViewModel
import com.onix.internship.okucherenko.ui.uni.UniViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ClubViewModel() }
    viewModel { BlackViewModel() }
    viewModel { MeadowViewModel() }
    viewModel { UniViewModel() }
    viewModel { LecturehallViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }

}