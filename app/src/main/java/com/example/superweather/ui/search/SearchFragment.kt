package com.example.superweather.ui.search

import android.app.Activity
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.superweather.R
import com.example.superweather.constants.Constants
import com.example.superweather.data.repository.sharedPref.SharedPrefRepository
import com.example.superweather.databinding.FragmentSearchBinding
import com.example.superweather.ui.base.BaseFragment
import com.example.superweather.ui.home.HomeFragmentDirections
import com.example.superweather.util.extension.NavigationFragmentUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException


class SearchFragment : BaseFragment() {
    private lateinit var lat : String
    private lateinit var lon: String
    private lateinit var viewDataBinding: FragmentSearchBinding
    private lateinit var sharedPrefRepository: SharedPrefRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        sharedPrefRepository = SharedPrefRepository(requireContext())

        viewDataBinding.searchCity.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if(query != null) {
                    getLatLngFromCityName(query)
                } else {
                   Toast.makeText(requireContext(), "City not found", Toast.LENGTH_LONG).show()
                }

                return false
            }

            override fun onQueryTextChange(newCity: String?): Boolean {
                return false
            }
        })

        viewDataBinding.cancelSearch.setOnClickListener {
            navigateBack()
        }

        return viewDataBinding.root
    }

    private fun navigateBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }


    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
    }


    fun getLatLngFromCityName(cityName: String) {
        val geocoder = Geocoder(requireContext())

        try {
            val addresses = geocoder.getFromLocationName(cityName, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val lat = addresses[0].latitude
                    val lon = addresses[0].longitude
                   sharedPrefRepository.savePair(Constants.SHARED_PREF_LAT_LONG_PAIR, Pair(lat, lon))
                    navigateToHome()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun navigateToHome() {
        val action = SearchFragmentDirections.actionSearchFragmentToHomeFragment()
        NavigationFragmentUtil().navigateToFragmentWithAction(requireView(), action)
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        } else {
            requireActivity().overrideActivityTransition(Activity.OVERRIDE_TRANSITION_CLOSE, R.anim.fadein, R.anim.fadeout)
        }
    }


}
