package com.example.assign4_pbm5_hangman

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle


class MainActivityViewModel: ViewModel() {
    lateinit var imgView: ImageView
    lateinit var wordView: TextView
    lateinit var hintButton: Button
    var timeToRemoveHalf = false //Mak here
    var timeToRemoveVowels = false
    var wonTheGame = false
    var lostTheGame = false
    var hintCount: Int = 0
    var wordNow: String = "______"
    var the_word: String = ""
    var hang_state: Int = 0
    val Words = listOf("RONALD", "ACORNS", "BOXING", "BRONZE", "HIDDEN", "QUIRKY")


}