package com.example.broadcastreceiver

import android.app.*
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationManager {

    private const val CHANNEL_ID = "channel_id"
    private const val CHANNEL_NAME = "channel_name"
    private const val NOTIFICATION_ID = 0

    fun channel(context: Context){
        val notificationManager = NotificationManagerCompat.from(context)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun notification(context: Context, message: String){
        val intent = Intent(context,MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context).run{
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID).setDefaults(
            Notification.DEFAULT_ALL
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(message)
            .setColor(Color.GREEN)
            .setAutoCancel(false)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID,notification)
    }

}