package com.example.weatherdataapp

import com.example.weatherdataapp.Data.WeatherData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val API_KEY = "abec7b3b1225efd20e83e8d1a6ec0dc7"

//http://api.weatherstack.com/current?access_key=abec7b3b1225efd20e83e8d1a6ec0dc7&query=New%20York

interface APIService {

    @GET("current")
    fun getWeather(
        @Query("query") location: String
    ): Deferred<WeatherData>


    companion object {
        operator fun invoke(): APIService {
            val requestInterceptor = Interceptor { chain ->
                val apiKey = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(apiKey)
                    .build()
                 chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }
}
