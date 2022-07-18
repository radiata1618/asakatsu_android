package com.app.asakatsu_android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.io.File
import androidx.core.content.ContextCompat.startActivity
import java.time.LocalDate
import java.time.LocalDateTime


class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // toast で受け取りを確認



        val fileName = "nextTimeInfo.txt"
        if (File(context.cacheDir, fileName).exists()) {
            val text = File(context.cacheDir, fileName).readText()
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
            val nowDateTime = LocalDateTime.now()
            val nextAlarmTime = LocalDateTime.of(
                text.substring(0, 4).toInt(),
                text.substring(4, 6).toInt(),
                text.substring(6, 8).toInt(),
                text.substring(8, 10).toInt(),
                text.substring(10, 12).toInt(),
                0
            )

            if(nowDateTime.isAfter(nextAlarmTime)){
                Toast.makeText(context, "ALREADY", Toast.LENGTH_LONG).show()
                startMainActivity(context)
            }else{

                Toast.makeText(context, "NOT YET", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(context, "NOT EXISTS", Toast.LENGTH_LONG).show()
        }
    }

    fun startMainActivity(context: Context) {
        val intent: Intent = Intent(context, MainActivity().javaClass)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(context, intent, null)
    }
}