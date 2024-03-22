package com.example.superweather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.example.superweather.R
import com.example.superweather.databinding.FragmentHomeBinding
import com.example.superweather.ui.base.BaseFragment


class HomeFragment : BaseFragment() {
   private val viewModel: HomeViewModel by viewModels()
   private lateinit var viewDataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       viewDataBinding =
           DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        viewDataBinding.viewmodel= viewModel
        viewDataBinding.lifecycleOwner = this@HomeFragment.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        viewModel.weatherData.observe(this) { it ->
            viewDataBinding.weather = it
        }
    }



}