package com.example.batteryremain.ui

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.batteryremain.BatteryChangedReceiver
import com.example.batteryremain.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // screen on
//        val screenOnReceiver : BroadcastReceiver = ScreenOnReceiver()
//        val filter1 = IntentFilter().apply {
//            addAction(Intent.ACTION_SCREEN_ON)
//        }
//        registerReceiver(screenOnReceiver, filter1)

        // battery changed
        val batteryChangedReceiver : BroadcastReceiver = BatteryChangedReceiver(viewModel)
        val filter2 = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
        }
        registerReceiver(batteryChangedReceiver, filter2)

        viewModel.data.observe(this, Observer {
            binding.textView.text = "배터리 잔량 : $it %"
        })

    }
}