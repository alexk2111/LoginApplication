package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { PreferenceStorage(get()) }
    single { WeatherRepository() }
}