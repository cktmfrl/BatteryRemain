package com.example.batteryremain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ScreenOnReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_SCREEN_ON)) {
            var toast = Toast.makeText(context, "good", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}