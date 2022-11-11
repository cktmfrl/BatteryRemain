package com.example.batteryremain.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log

fun getNetworkCallback(context: Context): ConnectivityManager.NetworkCallback {
    return object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            showToast(context, "wifi available")
            Log.d(context.javaClass.simpleName, "The default network is now: $network")
        }

        override fun onLost(network: Network) {
            showToast(context, "wifi unavailable")
            Log.d(context.javaClass.simpleName, "The application no longer has a default network. The last default network was $network")
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
//        showToast(
//            context,
//            "The default network changed capabilities: $networkCapabilities"
//        )
            Log.d(
                context.javaClass.simpleName,
                "The default network changed capabilities: ${networkCapabilities.transportInfo}"
            )
        }

        override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
//        showToast(
//            context,
//            "The default network changed link properties: $linkProperties"
//        )
            Log.d(
                context.javaClass.simpleName,
                "The default network changed link properties: $linkProperties"
            )
        }
    }
}