package com.androidapps.composeMVVM.app.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import timber.log.Timber
import javax.inject.Inject

/**
 * Utility class to check the network connectivity status of the application.
 * This class determines if the device is currently online by checking various network types.
 */
class NetworkConnection @Inject constructor(private val application: Application) {

    /**
     * Checks if the device is currently online.
     *
     * @return `true` if the device has an active network connection (Cellular, WiFi, Ethernet),
     *         `false` otherwise.
     */
    fun isOnline(): Boolean {
        // Obtain the ConnectivityManager service
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Check network capabilities
        connectivityManager.let {
            val capabilities =
                it.getNetworkCapabilities(it.activeNetwork)
            if (capabilities != null) {
                // Check for various types of network transport
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Timber.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Timber.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Timber.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        // If no known network capabilities are found, assume no connection
        return false
    }
}