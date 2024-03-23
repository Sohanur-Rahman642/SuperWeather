package com.example.superweather.util.location

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.superweather.constants.Constants
import com.example.superweather.data.repository.sharedPref.SharedPrefRepository
import com.google.android.gms.location.FusedLocationProviderClient

object LocationUtils {
    fun getLastKnownLocation(
        fragment: Fragment,
        fusedLocationClient: FusedLocationProviderClient,
        sharedPrefRepository: SharedPrefRepository,
    ) {
        if (ActivityCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                fragment.requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                Constants.REQUEST_LOCATION_PERMISSION
            )
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val lat = location.latitude.toString().trim()
                    val lon = location.longitude.toString().trim()
                    sharedPrefRepository.savePair(
                        Constants.SHARED_PREF_LAT_LONG_PAIR,
                        Pair(lat, lon)
                    )
                } else {
                    Toast.makeText(
                        fragment.requireContext(),
                        "Location not found",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}
