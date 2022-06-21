package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.arch.provider.TextResProvider
import org.koin.dsl.module

val providerModule = module {
    single { TextResProvider(get()) }
}