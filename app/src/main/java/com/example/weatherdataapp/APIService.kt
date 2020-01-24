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

const val API_KEY = "abec7b3b1225efd20e83e8d1a6ec0dc7"

//http://api.weatherstack.com/current?access_key=abec7b3b1225efd20e83e8d1a6ec0dc7&query=New%20York

interface APIService {

    @GET("current")
    fun getWeather(
        @Query("query") location: String,
        @Query("lang") languageCode: String = "en"
    ): Deferred<WeatherData>


    companion object {
        operator fun invoke(): APIService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
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
