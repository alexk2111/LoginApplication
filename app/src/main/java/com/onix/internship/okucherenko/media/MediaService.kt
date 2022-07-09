package com.onix.internship.okucherenko.media

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.onix.internship.okucherenko.R

class MediaService : Service() {

    private var mediaPlayer = MediaPlayer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer = MediaPlayer.create(this, R.raw.the_question_game_illurock)
        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}