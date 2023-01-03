package com.hectorfortuna.tmdbapp.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData

class ConnectivityWatcher(
    private val context: Context,
): LiveData<Boolean>(){
    private lateinit var networkCallback: NetworkCallback
    override fun onActive() {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkCallback = getNetworkCallback()
        manager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onInactive() {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.unregisterNetworkCallback(networkCallback)
    }

    private fun getNetworkCallback() = object : NetworkCallback() {
        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            networkCapabilities.run {
                if (hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )
                ) {
                    postValue(true)
                } else if (hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && !hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )
                ) {
                    postValue(false)
                } else if (!hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED)) {
                    postValue(false)
                }
            }
        }
    }
}