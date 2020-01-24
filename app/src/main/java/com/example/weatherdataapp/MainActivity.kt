package com.example.weatherdataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var weather_view = findViewById(R.id.WeatherView) as TextView
        val apiService = APIService()
        GlobalScope.launch(Dispatchers.Main) {
            val weather= apiService.getWeather("Minneapolis").await()

            weather_view.text = weather.current.toString()+weather.location.toString()
        }
    }
}
