package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.ui.details.DetailsViewModel
import com.onix.internship.okucherenko.ui.main.MainViewModel
import com.onix.internship.okucherenko.ui.result.ResultViewModel
import com.onix.internship.okucherenko.ui.search.SearchViewModel
import com.onix.internship.okucherenko.ui.search.extended.ExtendedViewModel
import com.onix.internship.okucherenko.ui.search.simple.SimpleViewModel
import com.onix.internship.okucherenko.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailsViewModel() }
    viewModel { ResultViewModel() }
    viewModel {ExtendedViewModel() }
    viewModel { SimpleViewModel() }
    viewModel { SearchViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
}