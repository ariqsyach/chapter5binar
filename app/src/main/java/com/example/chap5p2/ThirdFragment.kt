package com.example.chap5p2


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_landing_page.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_third.*

/**
 * A simple [Fragment] subclass.
 */
class ThirdFragment : Fragment() {
    companion object {
        const val REQUEST_CODE = 1
        const val OPEN_CAMERA = 9
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            savedInstanceState.getString("name")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view)
            .load("https://i.ya-webdesign.com/images/avatar-png-1.png")
            .into(potouser)
        Log.d("Start", "ThirdFragment")

        btn_next.setOnClickListener {
            val intent = Intent(this@ThirdFragment.context, PilihActivity::class.java)
            val value = inputNama.text.toString()
            Log.d("Input", "Nama Pemain")
            if (inputNama.length() > 0) {
                intent.putExtra("name", value)
                startActivity(intent)

            } else {
                Toast.makeText(this.context, "Silahkan Masukkan Nama", Toast.LENGTH_LONG).show()
                Log.d("Input", "Masih Kosong")
            }
        }
    }
}



