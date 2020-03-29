package com.example.chap5p2

import android.annotation.SuppressLint
import android.content.Intent
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

class MainActivity : AppCompatActivity(), CallBack {

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
        Log.d("Start", "MainActivity")

        val ubahNama = intent.getStringExtra("name")
        playerName.text = "${ubahNama}"
        cpuName.text = "CPU"
        btnPlayer1 = mutableListOf(gunting1, batu1, kertas1)
        btnPlayer2 = mutableListOf(gunting2, batu2, kertas2)

        homeBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, PilihActivity::class.java)
            val value = playerName.text.toString()
                intent.putExtra("name", value)
            startActivity(intent)
            Log.d("Kembali", "PilihActivity")
        }
        exitButton.setOnClickListener{
            finishAffinity()
            Log.d("Exit", "Exit")
        }
    }

    fun resetbtn(v: View) {
        tlstengah.setImageResource(R.drawable.vs)
        btnPlayer1.forEach {
            it.isEnabled = true
            it.background = getDrawable(android.R.color.transparent)
        }
        btnPlayer2.forEach {
            it.isEnabled = true
            it.background = getDrawable(android.R.color.transparent)
        }
        Log.d("Reset", "MainActivity")
        data.clear()
    }

    fun pilPlayerTwo(v: View) {
        Toast.makeText(this, "Jika Ingin Melawan Player 2 Silahkan Ubah Mode", Toast.LENGTH_LONG)
            .show()
    }

    fun pilPlayerOne(v: View) {
        btnPlayer1.forEach {
            it.isEnabled = false
        }
        v.background = getDrawable(R.color.colorPrimaryDark)
        data.add(v.contentDescription.toString())

        btnPlayer2.forEach {
            it.isEnabled = false
        }
        val cpu = btnPlayer2.random()
        control.operasi(data[0], cpu.contentDescription.toString())
        cpu.background = getDrawable(R.color.colorPrimaryDark)
        Log.d("PlayerSatu", "Dipilih")
    }
}