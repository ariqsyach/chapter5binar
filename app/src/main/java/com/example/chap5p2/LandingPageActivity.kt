package com.example.chap5p2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_landing_page.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_third.*

class LandingPageActivity : AppCompatActivity() {
    companion object {
        var TAG = ""
    }
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "On Create")
        setContentView(R.layout.activity_landing_page)


        val adapterView = AdapterViewPager(supportFragmentManager)
        view_pager.adapter = adapterView
        dots_indicator.setViewPager(view_pager)
//        if (savedInstanceState!=null){
//            val name = savedInstanceState.getString("name") as String
//            tv_name.text = name
//        }
      //  btn_next.setOnClickListener {
          //  val value = editText.text.toString()
       //     val intent = Intent(this, MainActivity::class.java)
          //  intent.putExtra("name", value)
       //     startActivity(intent)
    //    }
//        btnsave.setOnClickListener {
//            name = "Ariq Syach"
//            tv_name.text = name
//        }
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("name","Ariq Syach")
  }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "On Start")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "On Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "On Stop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "On Destroy")
    }
}




