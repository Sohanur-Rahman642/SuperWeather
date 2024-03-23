package com.example.superweather.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.superweather.data.repository.sharedPref.SharedPrefRepository
import com.example.superweather.util.location.LocationUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

abstract class BaseFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var sharedPrefRepository: SharedPrefRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        sharedPrefRepository = SharedPrefRepository(this.requireContext())
        LocationUtils.getLastKnownLocation(this, fusedLocationClient, sharedPrefRepository)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModelObservers()
    }


    open fun setupViewModelObservers() {}
}