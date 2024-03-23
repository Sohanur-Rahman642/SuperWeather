package com.example.superweather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConnectivityViewModel : ViewModel() {
    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean>
        get() = _isConnected

    fun setConnectivityStatus(isConnected: Boolean) {
        _isConnected.value = isConnected
    }
}
