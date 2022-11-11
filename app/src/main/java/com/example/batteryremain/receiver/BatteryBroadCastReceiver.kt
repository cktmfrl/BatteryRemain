package com.example.batteryremain.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import com.example.batteryremain.ui.main.MainViewModel

class BatteryBroadCastReceiver(val viewModel: MainViewModel) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_BATTERY_CHANGED)) {
            val remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) // 현재 충전량

            viewModel.data.value = remain

//            var toast = Toast.makeText(context, "현재 충전량 : $remain", Toast.LENGTH_SHORT)
//            toast.show()
        }
    }
}