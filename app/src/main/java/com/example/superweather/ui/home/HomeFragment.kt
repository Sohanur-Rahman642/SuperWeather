package com.example.superweather.ui.home

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.superweather.R
import com.example.superweather.constants.Constants
import com.example.superweather.data.model.weather.Weather
import com.example.superweather.data.repository.sharedPref.SharedPrefRepository
import com.example.superweather.databinding.FragmentHomeBinding
import com.example.superweather.ui.base.BaseFragment
import com.example.superweather.util.extension.NavigationFragmentUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale


class HomeFragment : BaseFragment() {

    private lateinit var sharedPrefRepository: SharedPrefRepository

    private lateinit var latLongPair: Pair<String, String>

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewDataBinding: FragmentHomeBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sharedPrefRepository = SharedPrefRepository(this.requireContext())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        latLongPair =  sharedPrefRepository
            .retrievePair(Constants.SHARED_PREF_LAT_LONG_PAIR, Pair(Constants.DUMMY_LATITUDE, Constants.DUMMY_LONGITUDE))

        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(latLongPair.first, latLongPair.second)
        )[HomeViewModel::class.java]

       viewDataBinding =
           DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewDataBinding.contentShimmerLayout.startShimmer()
        viewDataBinding.search.setOnClickListener{navigateToSearch()}

        viewDataBinding.viewmodel= viewModel
        viewDataBinding.lifecycleOwner = this@HomeFragment.viewLifecycleOwner

        getAddress(latLongPair.first.toDouble(), latLongPair.second.toDouble())

        return viewDataBinding.root
    }

    private fun navigateToSearch() {
        val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
        NavigationFragmentUtil().navigateToFragmentWithAction(requireView(), action)
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        viewModel.weatherData.observe(this) { it ->
            if(it != null){
                viewDataBinding.weather = it
                setWeatherIcon(it.weather.first())

                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000) // Delay for 2 seconds (2000 milliseconds)
                    viewDataBinding.contentShimmerLayout.stopShimmer()
                    viewDataBinding.contentShimmerContainer.visibility = View.GONE
                    viewDataBinding.contentItemLayout.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun getAddress(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val address = addresses[0]
                    viewDataBinding.cityName.text = address?.locality?:address?.subAdminArea
                    viewDataBinding.countryName.text = address?.countryName

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }


    private fun setWeatherIcon(weather: Weather) {
        when (weather.id) {
            in 200..232 -> viewDataBinding.weatherIcon.setImageResource(R.drawable.icon_weather_thunderstorm_cloud)
            in 300..321 -> viewDataBinding.weatherIcon.setImageResource(R.drawable.icon_weather_rain_cloud)
            in 500..531 -> viewDataBinding.weatherIcon.setImageResource(R.drawable.icon_weather_sun_rain_cloud)
            in 701..781 -> viewDataBinding.weatherIcon.setImageResource(R.drawable.icon_weather_cloud_sun)
            in 600..622 -> viewDataBinding.weatherIcon.setImageResource(R.drawable.icon_weather_snow_cloud)
            800 -> viewDataBinding.weatherIcon.setImageResource(R.drawable.icon_weather_sun)
            in 801..804 -> viewDataBinding.weatherIcon.setImageResource(R.drawable.icon_weather_cloud)
            else -> viewDataBinding.weatherIcon.setImageResource(R.drawable.icon_weather_sun)
        }
    }


}