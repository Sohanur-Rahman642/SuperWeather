package com.example.superweather.data.remote

import com.example.superweather.data.model.weather.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/weather")
    suspend fun fetchWeather(@Query("lat") lat: String, @Query("lon") lon: String): Call<WeatherResponse>
}