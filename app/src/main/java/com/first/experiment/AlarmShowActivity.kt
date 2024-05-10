package com.first.experiment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AlarmShowActivity : AppCompatActivity() {

    lateinit var click: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_show)

        click = findViewById(R.id.click)
        click.setOnClickListener {

            AlarmReceiver.ringtone.stop()

        }
    }
}