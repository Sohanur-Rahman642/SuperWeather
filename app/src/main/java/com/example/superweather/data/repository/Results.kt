package com.example.superweather.data.repository

import retrofit2.Response

sealed class Results<out T> {
    class Success<T>(response: Response<T>) : Results<T>(){
        val data = response.body()
    }

    class Failure<T>(response: Response<T>) : Results<T>() {
        val message: String? = response.errorBody().toString()
    }

    class Exception<T>(throwable: Throwable) : Results<T>() {
        val message: String? = throwable.localizedMessage
    }
}
