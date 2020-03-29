package com.example.chap5p2

import android.util.Log
import android.view.View

class Controller(var callBack: CallBack) {

    var hasil = 0

    fun operasi(playerSatu: String, computer: String) {

        if (playerSatu == "gunting" && computer == "kertas" || playerSatu == "batu" && computer == "gunting" || playerSatu == "kertas" && computer == "batu") {
            hasil = R.drawable.p1menang
            Log.d("hasil", "Player 1 Menang")
        } else if (playerSatu == "kertas" && computer == "gunting" || playerSatu == "gunting" && computer == "batu" || playerSatu == "batu" && computer == "kertas") {
            hasil = R.drawable.p2menang
            Log.d("hasil", "Player 2 Menang")
        } else if (playerSatu == "kertas" && computer == "kertas" || playerSatu == "gunting" && computer == "gunting" || playerSatu == "batu" && computer == "batu") {
            hasil = R.drawable.draw
            Log.d("hasil", "Permainan Seri")
        }

callBack.kirimBalik(hasil)
    }


}



