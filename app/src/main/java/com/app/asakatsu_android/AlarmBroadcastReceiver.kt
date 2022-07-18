
package com.app.asakatsu_android
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.io.File
import android.util.Log

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // toast で受け取りを確認

        Log.d("TAG", "Applemode中はオレンジボタンは無効")
        Toast.makeText(context, "Received ", Toast.LENGTH_LONG).show()


//        val fileName = "nextTimeInfo.txt"
//        val text = File(fileName).readText()
//        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}