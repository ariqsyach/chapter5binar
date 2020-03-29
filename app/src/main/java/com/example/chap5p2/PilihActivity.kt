package com.example.chap5p2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pilih.*
import kotlinx.android.synthetic.main.fragment_third.*

class PilihActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih)

        val name = intent.getStringExtra("name")
        txtVsPlyr.text = "${name} Bermain Melawan Player"
        txtVsComp.text = "${name} Bermain Melawan Computer"


        Glide.with(this)
            .load("https://i.ibb.co/3CwjgSN/vsplayer.png")
            .into(pilPlyr)

        Glide.with(this)
            .load("https://i.ibb.co/ZcRptjv/vscomputerr.png")
            .into(pilCom)

        Log.d("Start", "PilihActivity")


        pilCom.setOnClickListener {

            val intent = Intent(this@PilihActivity, MainActivity::class.java)
            val ubahNama = name
            intent.putExtra("name", ubahNama)
            startActivity(intent)
            Log.d("Pilih", "VS Computer")
        }
        pilPlyr.setOnClickListener{
            val intent = Intent(this@PilihActivity, MultiplayerActivity::class.java)
            val ubahNama = name
            intent.putExtra("name", ubahNama)
            startActivity(intent)
            Log.d("Pilih", "VS Player")
        }
    }
}