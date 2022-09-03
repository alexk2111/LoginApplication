package com.onix.internship.okucherenko.data.repository

import com.onix.internship.okucherenko.network.WeatherApi

class WeatherRepository {

    fun getCurrentWeather(city: String) = WeatherApi.retrofitWeatherApiService.getCurrentWeather(city)
}