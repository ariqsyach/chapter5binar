package com.example.chap5p2

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sp = getSharedPreferences("coba", Context.MODE_PRIVATE)
        val esp = sp.edit()
        esp.putBoolean("isLogin",false)
        esp.commit()

        val mediaPlayer = MediaPlayer.create(this, R.raw.ringtone)
        mediaPlayer.start()

        Handler().postDelayed({
            if (sp.getBoolean("isLogin", false)) {
                val intent = Intent(this, LandingPageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, PilihActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000)

    }
}

