package com.example.superweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.superweather.data.model.weather.WeatherResponse
import com.example.superweather.data.repository.WeatherRepository
import com.example.superweather.util.extension.liveDataBlockScope

class HomeViewModel :ViewModel() {
    private val weatherRepository = WeatherRepository()

    val weatherData: LiveData<WeatherResponse>
    private val lat = "23.8191441"
    private val lon = "90.45259540000006"

    init {
        weatherData = liveDataBlockScope {
            weatherRepository.loadWeatherData(lat, lon) {
                   it
            }
        }
    }

}