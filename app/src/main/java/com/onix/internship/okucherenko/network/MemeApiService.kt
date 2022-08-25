package com.onix.internship.okucherenko.network

import com.google.gson.Gson
import com.onix.internship.okucherenko.data.model.MemePage
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .baseUrl(BASE_URL)
    .build()

interface MemeApiService {
    @GET
    suspend fun getMemes(@Url url: String): MemePage
}

object MemeApi {
    val retrofitMemeService: MemeApiService by lazy {
        retrofit.create(MemeApiService::class.java)
    }
}
