package com.example.chap5p2

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val mediaPlayer = MediaPlayer.create(this, R.raw.ringtone)
        mediaPlayer.start()

        Handler().postDelayed( {
            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}
