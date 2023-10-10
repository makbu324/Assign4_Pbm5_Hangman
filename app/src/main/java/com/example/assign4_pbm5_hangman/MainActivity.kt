package com.example.assign4_pbm5_hangman

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

var wordNow: String = "______"
var the_word: String = "RONALD"
var hang_state: Int = 0

interface OnDataPass {
    fun onDataPass(data: String)
}

class MainActivity : AppCompatActivity(), OnDataPass {
    private lateinit var imgView: ImageView
    private lateinit var wordView: TextView
    var coordinatorLayout: CoordinatorLayout? = null

    var layout: CoordinatorLayout? = null

    fun hangman_state_return(num: Int, alphabet: String): Int {
        if (0 < num && num < 7)
            Toast.makeText(this,
                "Oops! \"" + alphabet + "\" is NOT in there...",
                Toast.LENGTH_SHORT
            ).show()
        if (num == 0) (return R.drawable.hangman_state_0)
        else if (num == 1) (return R.drawable.hangman_state_1)
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
        if (data in the_word) {
            var og = wordNow
            wordNow = ""
            for (i in the_word.indices) {
                if (the_word[i].toString() == data || the_word[i] == og[i])
                    wordNow += the_word[i].toString()
                else wordNow += "_"
            }
            wordView.text = wordNow
            if (wordNow == the_word) {
                imgView.setImageResource(R.drawable.hangman_state_won)
                Toast.makeText(this,
                    "YAY!! RHETT SAVES THE DAY!!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(this,
                    "\"" + data + "\" is in the word!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            hang_state++
            imgView.setImageResource(hangman_state_return(hang_state, data))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.hangman_state)
        wordView = findViewById(R.id.whats_the_word)

        wordView.text = wordNow
        imgView.setImageResource(hangman_state_return(hang_state, "#"))
    }

}