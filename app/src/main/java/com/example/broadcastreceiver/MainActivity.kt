package com.example.broadcastreceiver

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.broadcastreceiver.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var broadCastReceiver:BroadCastReceiver
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NotificationManager.channel(this)

        broadCastReceiver = BroadCastReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(broadCastReceiver,it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadCastReceiver)
    }
}