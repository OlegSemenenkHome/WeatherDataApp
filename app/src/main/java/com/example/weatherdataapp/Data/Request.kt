package com.example.weatherdataapp.Data


import com.squareup.moshi.Json

data class Request(
    @Json(name = "language")
    val language: String,
    @Json(name = "query")
    val query: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "unit")
    val unit: String
)