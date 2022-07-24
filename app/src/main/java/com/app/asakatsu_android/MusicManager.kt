package com.app.asakatsu_android

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle


class MusicManager(context: Context) {

    private lateinit var mediaPlayer: MediaPlayer
    private val myContext: Context = context

    fun startMusic() {
        mediaPlayer = MediaPlayer.create(myContext, R.raw.ring)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
    }
}