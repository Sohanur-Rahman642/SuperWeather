package com.example.superweather.ui.base


import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModelObservers()
    }

    open fun setupViewModelObservers() {}
}