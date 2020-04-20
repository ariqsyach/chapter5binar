package com.example.chap5p2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_exit.*
import kotlinx.android.synthetic.main.dialog_edit_memo.*

class ExitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit)

        tv_mulai.setOnClickListener{
            val intent = Intent(this@ExitActivity, SplashActivity::class.java)
            startActivity(intent)
            Log.d("Exit", "Mulai")
        }
        tv_keluar.setOnClickListener{
            finishAffinity()
            Log.d("Exit", "Exit")
        }
    }
}
