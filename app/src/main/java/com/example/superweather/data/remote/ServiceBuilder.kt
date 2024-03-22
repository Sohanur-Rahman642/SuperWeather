package com.example.superweather.data.remote

import com.example.superweather.BuildConfig
import com.example.superweather.constants.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val okHTTP = OkHttpClient.Builder().addInterceptor(RequestIntetceptor())
    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHTTP.build()).build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }


    internal class RequestIntetceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
           val oldRequest = chain?.request()
           val url = oldRequest?.url()?.newBuilder()
               ?.addQueryParameter("appid",  BuildConfig.OPEN_WEATHER_MAP_API_KEY)
               ?.build()
           val newRequest = oldRequest?.newBuilder()?.url(url)?.build()
           return chain.proceed(newRequest)
        }
    }
}