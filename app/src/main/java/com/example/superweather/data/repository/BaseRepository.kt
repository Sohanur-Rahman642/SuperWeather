package com.example.superweather.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.superweather.data.repository.result.onException
import com.example.superweather.data.repository.result.onFailure
import com.example.superweather.data.repository.result.onSuccess
import com.example.superweather.data.repository.result.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

abstract class BaseRepository {
    protected suspend fun <Type> loadCall(
        call: () -> Call<Type>,
        result: MutableLiveData<Type>,
        errorText: (String) -> Unit
    ) =
        withContext(Dispatchers.IO){
            call().request { response ->
                response.onSuccess { data?.let {
                    result.postValue(it)}
                }
                response.onFailure { message?.let { errorText(it) } }
                response.onException { message?.let { errorText(it) } }
            }

            result.apply { postValue(null) }
        }
}