package com.mkiperszmid.emptyapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MyApp : Application() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "notification_fcm"
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.messaging.token.addOnCompleteListener {
            if (!it.isSuccessful) {
                println("El token no fue generado")
                return@addOnCompleteListener
            }
            val token = it.result
            println("El token es> $token")
        }
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notificaciones de FCM",
                NotificationManager.IMPORTANCE_HIGH,
            )
            channel.description = "Estas notificaciones van a ser recibidas desde FCM"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
