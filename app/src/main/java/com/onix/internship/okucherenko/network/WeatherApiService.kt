package com.onix.internship.okucherenko.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.weatherapi.com/v1/current.json?key=8e2d92541ad842f48ab52158220309&q=Kirovohrad&aqi=no
private const val BASE_URL = "http://api.weatherapi.com/"
val contentType = "application/json".toMediaType()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(contentType))
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("/v1/current.json?key=8e2d92541ad842f48ab52158220309&aqi=no")
    fun getCurrentWeather(@Query("q") city: String)
}

object WeatherApi {
    val retrofitWeatherApiService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}