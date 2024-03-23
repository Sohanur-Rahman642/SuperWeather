package com.example.superweather.data.repository.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.superweather.constants.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefRepository(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SUPER_WEATHER_PREF,
        Context.MODE_PRIVATE
    )

    val gson = Gson()

    fun <T, U> savePair(key: String, pair: Pair<T, U>) {
        val json = gson.toJson(pair)
        sharedPreferences.edit().putString(key, json).apply()
    }

    inline fun <reified T, reified U> retrievePair(key: String, defaultValue: Pair<T, U>): Pair<T, U> {
        val json = sharedPreferences.getString(key, null)
        return if (json != null) {
            gson.fromJson(json, object : TypeToken<Pair<T, U>>() {}.type)
        } else {
            defaultValue
        }
    }

}