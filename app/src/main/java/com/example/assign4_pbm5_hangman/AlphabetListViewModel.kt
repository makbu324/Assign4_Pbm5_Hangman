package com.example.assign4_pbm5_hangman

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class AlphabetListViewModel : ViewModel() {

    var a_list = mutableListOf<String>("A","B","C","D","E","F",
        "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")

    init {
        for (i in 0 until 100) {
            val alphabet = "a"

            a_list += alphabet
        }
    }
}
