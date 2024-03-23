package com.example.superweather.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.superweather.R
import com.example.superweather.databinding.FragmentSearchBinding
import com.example.superweather.ui.base.BaseFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class SearchFragment : BaseFragment() {
    private lateinit var lat : String
    private lateinit var lon: String
    private lateinit var viewDataBinding: FragmentSearchBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        return viewDataBinding.root
    }


    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
    }




}
