package com.example.batteryremain.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var data: MutableLiveData<Int> = MutableLiveData<Int>()

    // https://yuar.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-AAC-ViewModel
//    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context, intent: Intent?) {
//            val connected: Boolean = isNetworkConnected(context)
//            if (connected) {
//                context.unregisterReceiver(this)
//            }
//            networkConnectedLiveData.setValue(connected)
//        }
//    }

}