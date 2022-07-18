
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

        Toast.makeText(context, "Received ", Toast.LENGTH_LONG).show()


        val fileName = "nextTimeInfo.txt"
        Log.d("■■■■■■■", "11111111111111")
        Log.d("■■■■■■■", context.cacheDir.absolutePath)
        if(File(context.cacheDir, fileName).exists()){
            val text = File(context.cacheDir, fileName).readText()
            Log.d("■■■■■■■", text)
//        val text = File(fileName).readText()
            Log.d("■■■■■■■", "22222222222")
            Log.d("■■■■■■■", text)
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }else{

            Toast.makeText(context, "NOT EXISTS", Toast.LENGTH_LONG).show()
        }
    }
}