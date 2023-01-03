package com.hectorfortuna.tmdbapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hectorfortuna.tmdbapp.R
import com.hectorfortuna.tmdbapp.core.ViewManager
import com.hectorfortuna.tmdbapp.core.network.ConnectivityWatcher
import com.hectorfortuna.tmdbapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var connectivityWatcher: ConnectivityWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        navController.previousBackStackEntry

        connectivityWatcher = ConnectivityWatcher(this)
        connectivityWatcher.observeForever() { networkState ->
            ViewManager.networkFavouriteState = networkState
        }

    }

}