package com.app.asakatsu_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
//https://qiita.com/jamestong/items/50dd0977a76e83bad280

class MainActivity : AppCompatActivity() {

    private lateinit var counterLabel: TextView
    private lateinit var receivedValueLable: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterLabel = findViewById(R.id.textViewCount)
        receivedValueLable = findViewById(R.id.textViewReceivedValue)
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