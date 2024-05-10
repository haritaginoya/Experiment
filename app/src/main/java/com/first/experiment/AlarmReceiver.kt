package com.first.experiment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.widget.Toast


class AlarmReceiver : BroadcastReceiver() {

    companion object {
        lateinit var ringtone: Ringtone
    }
    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context, "AlarmReceiver", Toast.LENGTH_SHORT).show()

//        var ab = Myclassaa()
        // we will use vibrator first
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(4000)
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show()
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }

        // setting default ringtone
        ringtone = RingtoneManager.getRingtone(context, alarmUri)

        // play ringtone
        ringtone.play()

//        Handler(Looper.getMainLooper()).postDelayed({
//            ringtone.stop()
//        }, 5000)

        val i = Intent(context, AlarmShowActivity::class.java)
        i.setFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
        )
        context.startActivity(i)

    }
}