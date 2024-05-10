package com.first.experiment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.TimeZone


class AlarmActivity : AppCompatActivity() {
    lateinit var alarmTimePicker: TimePicker
    lateinit var pendingIntent: PendingIntent
    lateinit var alarmManager: AlarmManager
    lateinit var toggleButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        alarmTimePicker = findViewById(R.id.timePicker);
        toggleButton = findViewById(R.id.toggleButton);
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        RequestPermission()

        toggleButton.setOnClickListener {

            var time: Long
            if (toggleButton.isChecked) {
                Toast.makeText(/* context = */ this, /* text = */
                    "ALARM ON (${alarmTimePicker.currentHour} - ${alarmTimePicker.currentMinute})", /* duration = */
                    Toast.LENGTH_SHORT
                ).show()
                val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault())

                // calendar is called to get current time in hour and minute
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.currentHour)
                calendar.set(Calendar.MINUTE, alarmTimePicker.currentMinute)
                calendar.set(Calendar.SECOND, 0)

                // using intent i have class AlarmReceiver class which inherits
                // BroadcastReceiver
                val intent = Intent(this, AlarmReceiver::class.java)

                // we call broadcast using pendingIntent
                pendingIntent = PendingIntent.getBroadcast(
                    this, 0, intent, PendingIntent.FLAG_IMMUTABLE
                )

                // Alarm rings continuously until toggle button is turned off
                //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent)
                alarmManager[AlarmManager.RTC, calendar.timeInMillis] = pendingIntent
                // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);
            } else {
                alarmManager.cancel(pendingIntent)
                Toast.makeText(this, "ALARM OFF", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun RequestPermission() {
        // Check if Android M or higher
        // Show alert dialog to the user saying a separate permission is needed
        // Launch the settings activity if the user prefers

        if (!Settings.canDrawOverlays(this)) {

            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")
            )
            startActivityForResult(intent, 1001)

        }


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1001) {
            if (resultCode != RESULT_OK) {
                finishAffinity()
            }
        }

    }

}