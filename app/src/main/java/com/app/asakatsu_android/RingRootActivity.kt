package com.app.asakatsu_android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.Vibrator
import android.widget.Button

class RingRootActivity : AppCompatActivity() {

    private val musicManager: MusicManager = MusicManager(this@RingRootActivity)
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ring_root)

//        val buttonStart: Button = findViewById(R.id.start_music)

        // Resister listener
//        buttonStart.setOnClickListener {
//            musicManager.startMusic()
//        }

        // Button for stop music
        val buttonStop: Button = findViewById(R.id.stop_music)

        // Resister listener
        buttonStop.setOnClickListener {
            musicManager.stopMusic()
            vibrator.cancel()
        }

        musicManager.startMusic()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val vibrationEffect = VibrationEffect.createOneShot(300, DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)
        } else {
            vibrator.vibrate(300)
        }
    }
}