package com.example.superweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superweather.data.model.weather.WeatherResponse
import com.example.superweather.data.repository.WeatherRepository
import com.example.superweather.util.extension.liveDataBlockScope
import kotlinx.coroutines.launch


//class HomeViewModelFactory(private val lat: String, private val lon: String) :
//    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return HomeViewModel(lat, lon) as T
//    }
//}

class HomeViewModel() :ViewModel() {
    private val weatherRepository = WeatherRepository()

    private var mutableWeatherData = MutableLiveData<WeatherResponse>()
    var weatherData: LiveData<WeatherResponse> = mutableWeatherData

    private var lat = "23.8191"
    private var lon = "90.4526"

    init {
        weatherData = liveDataBlockScope {
            weatherRepository.loadWeatherData(lat, lon) {it}
        }
    }


    fun fetchCurrentWeatherData(lat: String, lon: String) {
        weatherData = liveDataBlockScope {
            weatherRepository.loadWeatherData(lat, lon) {it}
        }

    }

}