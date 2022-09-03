package com.onix.internship.okucherenko.ui.home

import androidx.lifecycle.viewModelScope
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: WeatherRepository): BaseViewModel() {

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather(){
        viewModelScope.launch {
            try {
                repository.getCurrentWeather("Kirovohrad")
            } catch (e: Exception){

            }
        }
    }
}