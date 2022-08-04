package com.onix.internship.okucherenko.di

import com.onix.internship.okucherenko.arch.provider.TextResProvider
import com.onix.internship.okucherenko.data.repository.entity.Devices
import com.onix.internship.okucherenko.data.repository.entity.House
import org.koin.dsl.module

val providerModule = module {
    single { TextResProvider(get()) }
}