package com.example.chap5p2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_signup.setOnClickListener {
            val sp = getSharedPreferences("coba", Context.MODE_PRIVATE)
            val esp = sp.edit()

            esp.putString("name", et_nama.text.toString())
            esp.putString("email", et_email.text.toString())
            esp.putString("password", et_password.text.toString())
            esp.putBoolean("isRegister", true)
            esp.commit()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        val sp = getSharedPreferences("coba", Context.MODE_PRIVATE)

        if ( sp.getBoolean("isLogin", false)){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else if(sp.getBoolean("isRegister", false)){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}

