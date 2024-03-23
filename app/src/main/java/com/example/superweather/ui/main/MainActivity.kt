package com.example.superweather.ui.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.superweather.R
import com.example.superweather.util.network.ConnectivityChangeListener
import com.example.superweather.util.network.ConnectivityReceiver
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), ConnectivityChangeListener {
    private var connectivityReceiver: ConnectivityReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectivityReceiver = ConnectivityReceiver(this)

        // Register the receiver to listen for network connectivity changes
        registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }


    override fun onConnectivityChange(isConnected: Boolean) {
        if(isConnected){
            showNoInternetSnackbar("Your internet is restored")
        }else{
            showNoInternetSnackbar("You are offline")
        }
    }

    private fun showNoInternetSnackbar(text: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }

}