package com.example.chap5p2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_third.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sp = getSharedPreferences("coba", Context.MODE_PRIVATE)
        val esp = sp.edit()

        btn_login.setOnClickListener {
            if (sp.getString("name", "") == et_usernameLogin.text.toString()
                && sp.getString("password", "") == et_passwordLogin.text.toString()
            ) {

                esp.putBoolean("isLogin", true)
                esp.commit()

                val intent = Intent(this, PilihActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Username dan Password salah !", Toast.LENGTH_SHORT).show()
            }
        }
        btn_reset.setOnClickListener {
            et_usernameLogin.text.clear()
            et_passwordLogin.text.clear()
        }
        btn_hide.setOnClickListener {
            var clicked = true
            if (true) {
                et_passwordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                clicked = false
            } else if (false) {
                et_passwordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance())
                clicked = true
            }
        }
    }
}