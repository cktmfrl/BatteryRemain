package com.example.batteryremain.ui.main

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.batteryremain.receiver.BatteryBroadCastReceiver
import com.example.batteryremain.databinding.ActivityMainBinding
import com.example.batteryremain.receiver.ScreenBroadCastReceiver
import com.example.batteryremain.ui.ViewInit

class MainActivity : AppCompatActivity(), ViewInit {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    private var batteryReceiver: BatteryBroadCastReceiver? = null
    private var screenReceiver: ScreenBroadCastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initView()
    }

    override fun initView() {
        setContentView(binding.root)
        registerScreenReceiver()
        registerBatteryReceiver()
    }

    override fun onDestroy() {
        unRegisterScreenReceiver()
        unRegisterBatteryReceiver()
        super.onDestroy()
    }

    private fun registerBatteryReceiver() {
        batteryReceiver = BatteryBroadCastReceiver(viewModel)
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
        }
        this.registerReceiver(batteryReceiver, filter)

        viewModel.data.observe(this) {
            binding.textView.text = "배터리 잔량 : $it %"
        }
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

}