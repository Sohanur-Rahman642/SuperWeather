package com.example.superweather.ui.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.superweather.R
import com.example.superweather.constants.Constants
import com.example.superweather.data.repository.sharedPref.SharedPrefRepository
import com.example.superweather.databinding.FragmentPermissionBinding
import com.example.superweather.databinding.FragmentSearchBinding
import com.example.superweather.ui.base.BaseFragment
import com.example.superweather.util.extension.NavigationFragmentUtil
import com.example.superweather.util.location.LocationUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class PermissionFragment : BaseFragment() {
    private lateinit var viewDataBinding: FragmentPermissionBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var sharedPrefRepository: SharedPrefRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_permission, container, false)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        sharedPrefRepository = SharedPrefRepository(this.requireContext())

        viewDataBinding.enableLocation.setOnClickListener{
            LocationUtils.getLastKnownLocation(this, fusedLocationClient, sharedPrefRepository)
        }

        return viewDataBinding.root
    }
    

    private fun navigateToHome() {
        val action = PermissionFragmentDirections.actionPermissionFragmentToHomeFragment()
        NavigationFragmentUtil().navigateToFragmentWithAction(requireView(), action)
    }


    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
    }


}


