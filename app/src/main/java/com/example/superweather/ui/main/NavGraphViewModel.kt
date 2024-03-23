package com.example.superweather.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.superweather.R
import com.example.superweather.constants.Constants
import com.example.superweather.data.repository.sharedPref.SharedPrefRepository


class NavGraphViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NavGraphViewModel(context) as T
    }
}

class NavGraphViewModel(context: Context) : ViewModel() {
    private var sharedPrefRepository: SharedPrefRepository = SharedPrefRepository(context)

    fun getStartDestination(): Int {
        val dataAvailable = sharedPrefRepository
            .retrievePair(Constants.SHARED_PREF_LAT_LONG_PAIR, Pair(Constants.DUMMY_LATITUDE, Constants.DUMMY_LONGITUDE))

        return if (dataAvailable != null) {
            R.id.homeFragment
        } else {
            R.id.permissionFragment
        }
    }
}
