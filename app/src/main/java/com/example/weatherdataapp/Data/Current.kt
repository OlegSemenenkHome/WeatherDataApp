package com.example.weatherdataapp.Data


import com.squareup.moshi.Json

data class Current(
    @Json(name = "cloudcover")
    val cloudcover: Int,
    @Json(name = "feelslike")
    val feelslike: Int,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "is_day")
    val isDay: String,
    @Json(name = "observation_time")
    val observationTime: String,
    @Json(name = "precip")
    val precip: Double,
    @Json(name = "pressure")
    val pressure: Int,
    @Json(name = "temperature")
    val temperature: Int,
    @Json(name = "uv_index")
    val uvIndex: Int,
    @Json(name = "visibility")
    val visibility: Int,
    @Json(name = "weather_code")
    val weatherCode: Int,
    @Json(name = "weather_descriptions")
    val weatherDescriptions: List<String>,
    @Json(name = "weather_icons")
    val weatherIcons: List<String>,
    @Json(name = "wind_degree")
    val windDegree: Int,
    @Json(name = "wind_dir")
    val windDir: String,
    @Json(name = "wind_speed")
    val windSpeed: Int
)