package com.first.experiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NotificationReminder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_reminder)

        Notifications.createNotificationChannel(this)
        Notifications.setDailyNotificationAlarm(this,12,7,0)

    }
}