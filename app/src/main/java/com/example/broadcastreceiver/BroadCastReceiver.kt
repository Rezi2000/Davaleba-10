package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BroadCastReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isEnabled = intent?.getBooleanExtra("state",false)
        if (isEnabled == true && context!=null){
            NotificationManager.notification(context,"Airplane mode is enabled")
        }else{
            context?.let { NotificationManager.notification(it,"Airplane mode is disabled") }
        }
    }
}