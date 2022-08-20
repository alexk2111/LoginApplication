package com.onix.internship.okucherenko.network

import com.onix.internship.okucherenko.data.Songs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://xeno-canto.org"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SongsApiService {
    @GET("api/2/recordings")
    suspend fun getSongs(@Query("query", encoded = true) query: String, @Query("page") page: Int): Songs

}

object SongsApi {
    val retrofitService : SongsApiService by lazy {
        retrofit.create(SongsApiService::class.java) }
}