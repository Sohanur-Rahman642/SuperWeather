package com.example.superweather.ui.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.superweather.R
import com.example.superweather.ui.permission.PermissionFragmentDirections
import com.example.superweather.util.extension.NavigationFragmentUtil

class MainActivity : AppCompatActivity() {

    private val navGraphViewModel: NavGraphViewModel by viewModels{ NavGraphViewModelFactory(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val graphInflater = navController.navInflater
        var navGraph = graphInflater.inflate(R.navigation.navigation)
        navGraph.setStartDestination(navGraphViewModel.getStartDestination())
        navController.graph = navGraph

    }
}