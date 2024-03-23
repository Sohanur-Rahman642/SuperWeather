package com.example.superweather.util.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityReceiver(private val listener: ConnectivityChangeListener) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val isConnected = isNetworkConnected(context)
            listener.onConnectivityChange(isConnected)
        }
    }

    private fun isNetworkConnected(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}

interface ConnectivityChangeListener {
    fun onConnectivityChange(isConnected: Boolean)
}
