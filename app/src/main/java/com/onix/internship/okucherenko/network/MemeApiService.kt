package com.onix.internship.okucherenko.network

import com.google.gson.Gson
import com.onix.internship.okucherenko.data.model.MemePage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://podolyanbogdan.github.io/localememes/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .baseUrl(BASE_URL)
    .build()

interface MemeApiService {
    @GET("{page}")
    suspend fun getMemes(@Path("page") page: Int): MemePage
}

object MemeApi {
    val retrofitMemeService: MemeApiService by lazy {
        retrofit.create(MemeApiService::class.java)
    }
}
