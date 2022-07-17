package com.app.asakatsu_android


import androidx.multidex.MultiDexApplication
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

const val ENGINE_ID = "1"

class MyApplication : MultiDexApplication() {
    var count = 0
    var receivedValue = 0

    private lateinit var channel: MethodChannel

    override fun onCreate() {
        super.onCreate()

        val flutterEngine = FlutterEngine(this)
        flutterEngine
            .dartExecutor
            .executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )

        FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)

        channel = MethodChannel(flutterEngine.dartExecutor, "multiple-flutters")
        channel.invokeMethod("setCount",count + 3)
        channel.setMethodCallHandler { call, _ ->
            when (call.method) {
                "tapCounter" -> {
                    count++
                }
                "jump2Native" -> {
                    receivedValue = call.arguments as Int
                }
            }
        }
    }
}