package com.example.batteryremain.ui.main

import android.content.Intent
import android.content.IntentFilter
import android.net.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.batteryremain.receiver.BatteryBroadCastReceiver
import com.example.batteryremain.databinding.ActivityMainBinding
import com.example.batteryremain.receiver.ScreenBroadCastReceiver
import com.example.batteryremain.ui.ViewInit
import com.example.batteryremain.utils.getNetworkCallback


class MainActivity : AppCompatActivity(), ViewInit {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    private var batteryReceiver: BatteryBroadCastReceiver? = null
    private var screenReceiver: ScreenBroadCastReceiver? = null

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initView()

        viewModel.data.observe(this) {
            binding.textView.text = "배터리 잔량 : $it %"
        }
    }

    override fun initView() {
        setContentView(binding.root)
        registerScreenReceiver()
        registerBatteryReceiver()
        registerNetworkCallback()
    }

    override fun onDestroy() {
        unregisterNetworkCallback()
        unRegisterBatteryReceiver()
        unRegisterScreenReceiver()
        super.onDestroy()
    }

    // Broadcast Receiver
    private fun registerBatteryReceiver() {
        batteryReceiver = BatteryBroadCastReceiver(viewModel)
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
        }
        this.registerReceiver(batteryReceiver, filter)
    }

    private fun unRegisterBatteryReceiver() {
        if (batteryReceiver != null) {
            try {
                this.unregisterReceiver(batteryReceiver)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                batteryReceiver = null
            }
        }
    }

    private fun registerScreenReceiver() {
        screenReceiver = ScreenBroadCastReceiver()
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
        }
        this.registerReceiver(screenReceiver, filter)
    }

    private fun unRegisterScreenReceiver() {
        if (screenReceiver != null) {
            try {
                this.unregisterReceiver(screenReceiver)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                screenReceiver = null
            }
        }
    }

    // Network
    private fun registerNetworkCallback() {
        connectivityManager = getSystemService(ConnectivityManager::class.java)
        networkCallback = getNetworkCallback(this)

        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI) // Wi-fi
            //.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR) // 무선 네트워크
            .build()

        //connectivityManager.registerDefaultNetworkCallback(networkCallback)
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    private fun unregisterNetworkCallback() {
        if (networkCallback != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

}