package com.example.superweather.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.superweather.util.date.getCurrentDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@BindingAdapter("bind_atmosphere_pressure")
fun TextView.bindAtmosPherePressure(pressure: Int?) {
    if (pressure == null) return

    try {
        val atmPressure = pressure.toDouble() / 1013.25
        this.text = String.format("%.3f", atmPressure)
    }catch (e: Exception){
        this.text = pressure.toString()
    }
}

@BindingAdapter("bind_wind_value")
fun TextView.bindWindValue(speed: Double?) {
    if (speed == null) return

    try {
        val windSpeed = speed*2.237
        this.text = String.format("%.2f", windSpeed)
    }catch (e: Exception){
        this.text = speed.toString()
    }
}

@BindingAdapter("bind_sun_rise")
fun TextView.bindSunRise(timestamp: Int?) {
    println("bindSunRise timestamp $timestamp ")
    if (timestamp == null) return

    try {
        val sdf = SimpleDateFormat("HH:mm")
        sdf.timeZone = TimeZone.getDefault()
        val date = sdf.format(Date(timestamp * 1000L))
        this.text = date + " "
    }catch (e: Exception){
        this.text = "Not found"
    }
}

@BindingAdapter("bind_sun_set")
fun TextView.bindSunSet(timestamp: Int?) {
    println("bindSunSet timestamp $timestamp ")
    if (timestamp == null) return

    try {
        val sdf = SimpleDateFormat("HH:mm")
        sdf.timeZone = TimeZone.getDefault()
        val date = sdf.format(Date(timestamp * 1000L))
        this.text = date + " "
    }catch (e: Exception){
        this.text = "Not found"
    }
}


@BindingAdapter("bind_current_date")
fun TextView.bindCurrentDate(timestamp: Int?) {
    try {
        this.text = getCurrentDate()
    }catch (e: Exception){
        this.text = "Sat, Jan 01"
    }
}

