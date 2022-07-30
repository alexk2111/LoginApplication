package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.ui.emergency.EmergencyViewModel
import com.onix.internship.okucherenko.ui.help.HelpViewModel
import com.onix.internship.okucherenko.ui.main.MainViewModel
import com.onix.internship.okucherenko.ui.notelist.NoteViewModel
import com.onix.internship.okucherenko.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NoteViewModel(get ()) }
    viewModel { EmergencyViewModel() }
    viewModel { HelpViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }

}