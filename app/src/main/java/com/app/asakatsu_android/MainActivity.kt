package com.app.asakatsu_android

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.getBroadcast
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import java.util.*

//https://qiita.com/jamestong/items/50dd0977a76e83bad280

class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSION_CODE = 1

    // SYSTEM_ALERT_WINDOWが許可されているかのチェック
    fun isGranted(): Boolean {
        return Settings.canDrawOverlays(this)
    }

    // SYSTEM_ALERT_WINDOWの許可をリクエストする
    private fun requestPermission() {
        if (Settings.canDrawOverlays(this)) {
            // 許可されたときの処理
        } else {
            val uri: Uri = Uri.parse("package:$packageName")
            val intent: Intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
            startActivityForResult(intent, REQUEST_PERMISSION_CODE)
        }
    }

    // 許可されたかの確認は、onActivityResultでチェックする
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (Settings.canDrawOverlays(this)) {
                // 許可されたときの処理
            } else {
                // 拒否されたときの処理
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    private lateinit var counterLabel: TextView
    private lateinit var receivedValueLable: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterLabel = findViewById(R.id.textViewCount)
        receivedValueLable = findViewById(R.id.textViewReceivedValue)

    }

    private fun onClickJump2Flutter(){
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
            am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 5000,pending)
        }

        Toast.makeText(applicationContext, "set alarmManager", Toast.LENGTH_LONG).show()

        onClickJump2Flutter()

        requestPermission()

    }
}