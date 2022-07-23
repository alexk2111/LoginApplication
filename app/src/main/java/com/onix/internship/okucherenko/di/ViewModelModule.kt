package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.ui.age.AgeViewModel
import com.onix.internship.okucherenko.ui.category.CategoryViewModel
import com.onix.internship.okucherenko.ui.level.LevelViewModel
import com.onix.internship.okucherenko.ui.main.MainViewModel
import com.onix.internship.okucherenko.ui.map.MapViewModel
import com.onix.internship.okucherenko.ui.points.PointsViewModel
import com.onix.internship.okucherenko.ui.settings.SettingsViewModel
import com.onix.internship.okucherenko.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MapViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { PointsViewModel() }
    viewModel { CategoryViewModel() }
    viewModel { LevelViewModel() }
    viewModel { AgeViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
}