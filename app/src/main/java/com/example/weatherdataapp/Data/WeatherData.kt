package com.example.weatherdataapp.Data


import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "current")
    val current: Current,
    @Json(name = "location")
    val location: Location,
    @Json(name = "request")
    val request: Request
)