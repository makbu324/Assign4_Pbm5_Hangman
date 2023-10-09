package com.example.assign4_pbm5_hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast


interface OnDataPass {
    fun onDataPass(data: String)
}

class MainActivity : AppCompatActivity(), OnDataPass {
    private lateinit var imgView: ImageView

    var hang_state: Int = 0

    fun hangman_state_return(num: Int): Int {
        if (num == 1) (return R.drawable.hangman_state_1)
        else if (num == 2) (return R.drawable.hangman_state_2)
        else if (num == 3) (return R.drawable.hangman_state_3)
        else if (num == 4) (return R.drawable.hangman_state_4)
        else if (num == 5) (return R.drawable.hangman_state_5)
        else if (num == 6) (return R.drawable.hangman_state_6)
        else {
            Toast.makeText(this,
                "OH NO!! YOU LOST!!",
                Toast.LENGTH_LONG
            ).show()
            (return R.drawable.hangman_state_lost)
        }
    }

    override fun onDataPass(data: String) {
        hang_state++
        imgView.setImageResource(hangman_state_return(hang_state))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.hangman_state)
    }

}