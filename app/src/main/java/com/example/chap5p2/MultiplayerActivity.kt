package com.example.chap5p2

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.batu1
import kotlinx.android.synthetic.main.activity_main.batu2
import kotlinx.android.synthetic.main.activity_main.cpuName
import kotlinx.android.synthetic.main.activity_main.gunting1
import kotlinx.android.synthetic.main.activity_main.gunting2
import kotlinx.android.synthetic.main.activity_main.kertas1
import kotlinx.android.synthetic.main.activity_main.kertas2
import kotlinx.android.synthetic.main.activity_main.playerName
import kotlinx.android.synthetic.main.activity_main.refresh
import kotlinx.android.synthetic.main.activity_main.tlstengah

import kotlinx.android.synthetic.main.activity_pilih.*

class MultiplayerActivity : AppCompatActivity(), CallBack {

    override fun kirimBalik(hasil: Int) {
        tlstengah.setImageResource(hasil)
    }

    @SuppressLint("ResourceAsColor")

    private val data = mutableListOf<String>()
    private var btnPlayer1 = mutableListOf<ImageView>()
    private var btnPlayer2 = mutableListOf<ImageView>()
    var control = Controller(this)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ubahNama = intent.getStringExtra("name")
        playerName.text = "${ubahNama}"
        cpuName.text = "Player 2"
        btnPlayer1 = mutableListOf(gunting1, batu1, kertas1)
        btnPlayer2 = mutableListOf(gunting2, batu2, kertas2)
        btnPlayer2.forEach{
            it.isEnabled=false
        }

        homeBtn.setOnClickListener {
            val intent = Intent(this@MultiplayerActivity, PilihActivity::class.java)
            val value = playerName.text.toString()
            intent.putExtra("name", value)
            startActivity(intent)
            Log.d("Start", "MultiplayerActivity")
        }
        exitButton.setOnClickListener{
            finishAffinity()
            Log.d("Exit", "Exit")
        }
    }

    fun resetbtn( v : View ){
        tlstengah.setImageResource(R.drawable.vs)
        btnPlayer1.forEach {
            it.isEnabled = true
            it.background = getDrawable(android.R.color.transparent)
        }
        btnPlayer2.forEach {
            it.background = getDrawable(android.R.color.transparent)
        }

        data.clear()
        Log.d("Reset", "MultiplayerActivity")
    }


    fun pilPlayerOne(v: View) {
        btnPlayer1.forEach {
            it.isEnabled = false
        }
        btnPlayer2.forEach{
            it.isEnabled = true
        }
        v.background = getDrawable(R.color.colorPrimaryDark)
        data.add(v.contentDescription.toString())
        Log.d("Player1", "Dipilih")
        Toast.makeText(this, "Silahkan Pilih Player 2", Toast.LENGTH_SHORT).show()
    }

    fun pilPlayerTwo(v: View) {
        btnPlayer2.forEach {
            it.isEnabled = false
        }
        control.operasi(data[0], v.contentDescription.toString())
        v.background = getDrawable(R.color.colorPrimaryDark)
        Log.d("Player2", "Dipilih")
    }
}