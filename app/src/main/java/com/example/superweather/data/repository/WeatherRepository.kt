package com.example.superweather.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.superweather.data.model.weather.WeatherResponse
import com.example.superweather.data.remote.ServiceBuilder
import com.example.superweather.data.remote.WeatherService

class WeatherRepository : BaseRepository() {
    private val weatherService = ServiceBuilder.buildService(WeatherService::class.java)

    suspend fun loadWeatherData(lat: String, lon: String, errorText: (String) -> Unit) =
        loadCall(
            { weatherService.fetchWeather(lat, lon) },
            MutableLiveData<WeatherResponse>(),
            errorText
        )
}