package com.example.weatherdataapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var time_view = findViewById(R.id.WeatherViewTime) as TextView
        var location_view = findViewById(R.id.WeatherViewLocation) as TextView
        var temp_view = findViewById(R.id.WeatherViewTemp) as TextView
        val apiService = APIService()
        GlobalScope.launch(Dispatchers.Main) {
            val weather= apiService.getWeather("Minneapolis").await()

            time_view.text = weather.location.localtime
            location_view.text = weather.location.name+" , "+weather.location.region
            temp_view.text = weather.current.temperature.toString()

        }
    }
}
