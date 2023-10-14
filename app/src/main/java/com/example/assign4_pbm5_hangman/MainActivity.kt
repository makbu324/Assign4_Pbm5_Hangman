package com.example.assign4_pbm5_hangman
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity


interface OnDataPass {
    fun onDataPass(data: String)
    fun shouldRemHalfOrNot(): Boolean
    fun remhalfSuccess()

    fun getAnswer(): String
}

class MainActivity : AppCompatActivity(), OnDataPass {
    private lateinit var imgView: ImageView
    private lateinit var wordView: TextView
    private lateinit var hintButton: Button
    private var hintCount: Int = 0
    private var wordNow: String = "______"
    private var the_word: String = ""
    private var hang_state: Int = 0
    private val Words = listOf("RONALD", "ACORNS", "BOXING", "BRONZE", "HIDDEN", "QUIRKY")

    private val crimeListViewModel: AlphabetListViewModel by viewModels()

    private var timeToRemoveHalf = false //Mak here

    fun hangman_state_return(num: Int, alphabet: String): Int {
        if (alphabet != "HintIncPic")
            if (0 < num && num < 7)
                Toast.makeText(this,
                    "Oops! \"" + alphabet + "\" is NOT in there...",
                    Toast.LENGTH_SHORT).show()
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

    override fun shouldRemHalfOrNot(): Boolean {
        Log.d("HELLO", "KEEEP GOING")
        return timeToRemoveHalf
    }

    override fun remhalfSuccess() {
        timeToRemoveHalf = false
    }

    override fun getAnswer(): String {
        return the_word
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.hangman_state)
        wordView = findViewById(R.id.whats_the_word)
        hintButton = findViewById(R.id.hint_button)

        chooseword()
        wordNow = "______"
        hang_state = 0
        wordView.text = wordNow
        imgView.setImageResource(hangman_state_return(hang_state, "#"))

        hintButton.setOnClickListener{
            hintCount++
            HintTime()
        }
    }

    private fun chooseword(){
        the_word = Words.random()
    }
    private fun HintTime() {
        if (hang_state != 6) {
            if (hintCount == 1) {
                Toast.makeText(this, Hint4Word(the_word), Toast.LENGTH_LONG).show()
            }
            if (hintCount == 2) {
                //Will disable half of the remaining letters & costs a turn
                hang_state++
                imgView.setImageResource(hangman_state_return(hang_state, "HintIncPic"))

                //Mak here.
                timeToRemoveHalf = true
            }
            if (hintCount == 3) {
                //Will show all vowels & costs a turn will also disable all vowels
                var foundVowels = false
                for (i in the_word.indices) {
                    val char = the_word[i]
                    if (char in "AEIOU") {
                        onDataPass(char.toString())
                        foundVowels = true
                    }
                }
                for (char in "AEIOU")
                    crimeListViewModel.a_list.remove(char.toString())
                if (foundVowels) {
                    hang_state++
                    imgView.setImageResource(hangman_state_return(hang_state, "HintIncPic"))
                } else {
                    Toast.makeText(this, "No more vowels", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Hint not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun Hint4Word(word: String): String{
        return when(word){
            "RONALD" -> "Hint: A professor we all know"
            "ACORNS" -> "Hint: Grows on trees"
            "BOXING" -> "Hint: A sport"
            "BRONZE" -> "Hint: A metal"
            "HIDDEN" -> "Hint: Out of Sight"
            "QUIRKY" -> "Hint: A little weird"
            else -> "No hint available"
        }
    }
}