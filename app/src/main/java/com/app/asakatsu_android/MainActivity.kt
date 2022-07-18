package com.app.asakatsu_android

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.getBroadcast
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import java.util.*

//https://qiita.com/jamestong/items/50dd0977a76e83bad280

class MainActivity : AppCompatActivity() {
//
    private lateinit var counterLabel: TextView
    private lateinit var receivedValueLable: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterLabel = findViewById(R.id.textViewCount)
        receivedValueLable = findViewById(R.id.textViewReceivedValue)

//        // 時間をセットする
//        val calendar: Calendar = Calendar.getInstance()
//        // Calendarを使って現在の時間をミリ秒で取得
//        calendar.setTimeInMillis(System.currentTimeMillis())
//        // 5秒後に設定
//        calendar.add(Calendar.SECOND, 5)
//
//        //明示的なBroadCast
//        val intent = Intent(
//            applicationContext,
//            AlarmBroadcastReceiver::class.java
//        )
//        val pending: PendingIntent = getBroadcast(
//            applicationContext, 0, intent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )
//
//        // アラームをセットする
//        val am: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        if (am != null) {
////            am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 6000,pending)//60000
////            am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 5000,pending)
//            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
//
//            Toast.makeText(
//                applicationContext,
//                "Set Alarm ", Toast.LENGTH_SHORT
//            ).show()
//        }

    }

    fun onClickJump2Flutter(){
//        Log.d("MainActivity","onClickJump2Flutter clicked")
        val intent = FlutterActivity
            .withCachedEngine(ENGINE_ID)
            .build(this)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        // 時間をセットする
        val calendar: Calendar = Calendar.getInstance()
        // Calendarを使って現在の時間をミリ秒で取得
        calendar.setTimeInMillis(System.currentTimeMillis())
        // 5秒後に設定
        calendar.add(Calendar.SECOND, 1)

        //明示的なBroadCast
        val intent = Intent(
            applicationContext,
            AlarmBroadcastReceiver::class.java
        )
        val pending: PendingIntent = getBroadcast(
            applicationContext, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // アラームをセットする
        val am: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (am != null) {
//            am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 6000,pending)//60000
            am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 5000,pending)
//            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

            Toast.makeText(
                applicationContext,
                "Set Alarm ", Toast.LENGTH_SHORT
            ).show()
        }

        val app = application as MyApplication
        if (app.count != 0) {
            counterLabel.text = "Current tap times: ${app.count}"
        }
        if (app.receivedValue != 0){
            receivedValueLable.text = "Received value from flutter page: ${app.receivedValue}"
        }
        onClickJump2Flutter()

    }
}