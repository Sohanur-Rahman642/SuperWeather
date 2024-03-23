package com.example.superweather.util.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())
    return dateFormat.format(Date())
}