package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.ui.addtask.AddtaskViewModel
import com.onix.internship.okucherenko.ui.graphic.GraphicViewModel
import com.onix.internship.okucherenko.ui.profile.ProfileViewModel
import com.onix.internship.okucherenko.ui.home.HomeViewModel
import com.onix.internship.okucherenko.ui.main.MainViewModel
import com.onix.internship.okucherenko.ui.splash.SplashViewModel
import com.onix.internship.okucherenko.ui.task.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AddtaskViewModel() }
    viewModel { TaskViewModel() }
    viewModel { HomeViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { GraphicViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }

}