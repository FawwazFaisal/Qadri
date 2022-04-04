package com.example.qadri.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import com.example.qadri.R
import com.example.qadri.ui.activity.MainActivity
import com.example.qadri.constant.Constants.WORKOUT_COMPLETED_NOTIFICATION_ID

class NewAlarmReceiver : BroadcastReceiver() {
    var notificationManager: NotificationManager? = null
    var builder: Notification.Builder? = null
    fun createNotification(context: Context) {
        val icon: Int = R.drawable.qadrilogo
        val notificationIntent = Intent(
            context,
            MainActivity::class.java
        )
        val contentIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder = Notification.Builder(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "com.example.qadri",
                "My Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            // Configure the notification channel.
            notificationChannel.description = "Channel description"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager!!.createNotificationChannel(notificationChannel)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder!!.setContentIntent(contentIntent)
                .setSmallIcon(icon)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.qadrilogo
                    )
                )
                .setAutoCancel(true)
                .setOngoing(false)
                .setWhen(System.currentTimeMillis())
                .setOnlyAlertOnce(true)
                .setChannelId("com.example.qadri")
                .setContentTitle("Qadri")
                .setContentText("Completed !")
        } else {
            builder!!.setContentIntent(contentIntent)
                .setSmallIcon(icon)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.qadrilogo
                    )
                )
                .setAutoCancel(true)
                .setOngoing(false)
                .setWhen(System.currentTimeMillis())
                .setOnlyAlertOnce(true)
                .setContentTitle("Qadri")
                .setContentText("Completed !")
        }
        notificationManager!!.notify(WORKOUT_COMPLETED_NOTIFICATION_ID, builder!!.build())
    }

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotification(context)
    }
}