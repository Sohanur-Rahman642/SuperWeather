package com.example.superweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.superweather.data.model.weather.WeatherResponse
import com.example.superweather.data.repository.WeatherRepository
import com.example.superweather.util.extension.liveDataBlockScope
import kotlinx.coroutines.launch


class HomeViewModelFactory(private val lat: String, private val lon: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(lat, lon) as T
    }
}

class HomeViewModel(latitude: String, longitude: String) :ViewModel() {
    private val weatherRepository = WeatherRepository()

    private var mutableWeatherData = MutableLiveData<WeatherResponse>()
    var weatherData: LiveData<WeatherResponse> = mutableWeatherData

    private var lat = latitude
    private var lon = longitude

    init {
        weatherData = liveDataBlockScope {
            weatherRepository.loadWeatherData(lat, lon) {it}
        }
    }



}