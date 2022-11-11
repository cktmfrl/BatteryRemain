package com.example.batteryremain.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ScreenBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_SCREEN_ON)) {
            var toast = Toast.makeText(context, "screen on", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}