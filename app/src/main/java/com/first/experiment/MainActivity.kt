package com.first.experiment

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.HorizontalCalendarListener
import devs.mulham.horizontalcalendar.HorizontalCalendarView
import java.security.AccessController.getContext
import java.text.DateFormat
import java.util.Calendar
import java.util.Date


class MainActivity : AppCompatActivity() {

    lateinit var horizontalCalendar: HorizontalCalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        horizontalCalendar = findViewById(R.id.calendarView)

        val endDate: Calendar = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)
        val startDate: Calendar = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)
//
//        val horizontalCalendar: HorizontalCalendar =
//            HorizontalCalendar.Builder(this, R.id.calendarView).startDate(startDate.time)
//                .endDate(endDate.time).datesNumberOnScreen(5).build();


        val horizontalCalendar: HorizontalCalendar =
            HorizontalCalendar.Builder(this, R.id.calendarView).startDate(startDate.time).endDate(endDate.time)
                .datesNumberOnScreen(5)
                .build()


        horizontalCalendar.setCalendarListener(object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Date?, position: Int) {
                Toast.makeText(this@MainActivity,  " is selected!${DateFormat.getDateInstance().format(date)}", Toast.LENGTH_SHORT).show();
            }
        })


    }

}