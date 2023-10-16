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

    fun shouldRemVowels(): Boolean
    fun remhalfSuccess()

    fun remvowelSuccess()

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
    private lateinit var adapter: AlphabetListAdapter
    private val crimeListViewModel: AlphabetListViewModel by viewModels()
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private var timeToRemoveHalf = false //Mak here
    private var timeToRemoveVowels = false

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
            mainActivityViewModel.lostTheGame = true
            (return R.drawable.hangman_state_lost)
        }
    }

    override fun onDataPass(data: String) {
        if (mainActivityViewModel.wonTheGame || mainActivityViewModel.lostTheGame) {
            Toast.makeText(this,
                "The game has ended!",
                Toast.LENGTH_LONG
            ).show()
        }
        else if (data in mainActivityViewModel.the_word) {
            var og = mainActivityViewModel.wordNow
            mainActivityViewModel.wordNow = ""
            for (i in mainActivityViewModel.the_word.indices) {
                if (mainActivityViewModel.the_word[i].toString() == data || mainActivityViewModel.the_word[i] == og[i])
                    mainActivityViewModel.wordNow += mainActivityViewModel.the_word[i].toString()
                else mainActivityViewModel.wordNow += "_"
            }
            wordView.text = mainActivityViewModel.wordNow
            if (mainActivityViewModel.wordNow == mainActivityViewModel.the_word) {
                imgView.setImageResource(R.drawable.hangman_state_won)
                mainActivityViewModel.wonTheGame = true
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
            mainActivityViewModel.hang_state++
            imgView.setImageResource(hangman_state_return(mainActivityViewModel.hang_state, data))
        }
    }

    override fun shouldRemHalfOrNot(): Boolean {
        Log.d("HELLO", "KEEEP GOING")
        return mainActivityViewModel.timeToRemoveHalf
    }

    override fun shouldRemVowels(): Boolean {
        return mainActivityViewModel.timeToRemoveVowels
    }

    override fun remhalfSuccess() {
        mainActivityViewModel.timeToRemoveHalf = false
    }

    override fun remvowelSuccess(){
        mainActivityViewModel.timeToRemoveVowels = false
    }

    override fun getAnswer(): String {
        return mainActivityViewModel.the_word
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.hangman_state)
        wordView = findViewById(R.id.whats_the_word)
        hintButton = findViewById(R.id.hint_button)

        chooseword()
        wordNow = mainActivityViewModel.wordNow
        hang_state = mainActivityViewModel.hang_state
        wordView.text = wordNow
        imgView.setImageResource(hangman_state_return(mainActivityViewModel.hang_state, "#"))

        if (mainActivityViewModel.wonTheGame) {
            imgView.setImageResource(R.drawable.hangman_state_won)
        }
        else if (mainActivityViewModel.lostTheGame) {
            imgView.setImageResource(R.drawable.hangman_state_lost)
        }


        hintButton.setOnClickListener{
            mainActivityViewModel.hintCount++
            HintTime()
        }
    }

    private fun chooseword(){
        mainActivityViewModel.the_word = mainActivityViewModel.Words.random()
    }
    private fun HintTime() {
        if (mainActivityViewModel.hang_state != 6) {
            if (mainActivityViewModel.hintCount == 1) {
                Toast.makeText(this, Hint4Word(mainActivityViewModel.the_word), Toast.LENGTH_LONG).show()
            }
            if (mainActivityViewModel.hintCount == 2) {
                //Will disable half of the remaining letters & costs a turn
                mainActivityViewModel.hang_state++
                imgView.setImageResource(hangman_state_return(mainActivityViewModel.hang_state, "HintIncPic"))

                //Mak here.
                mainActivityViewModel.timeToRemoveHalf = true
            }
            if (mainActivityViewModel.hintCount == 3) {
                //Will show all vowels & costs a turn will also disable all vowels
                val vowels = "AEIOU"
                mainActivityViewModel.timeToRemoveVowels = true
                var foundVowels = false
                for(vowel in vowels){
                    if (vowel in mainActivityViewModel.the_word){
                        foundVowels = true

                        if (vowel.toString() in crimeListViewModel.a_list) {
                            onDataPass(vowel.toString())
                        }
                    }
                }
                if (foundVowels) {
                    mainActivityViewModel.hang_state++
                    imgView.setImageResource(hangman_state_return(mainActivityViewModel.hang_state, "HintIncPic"))
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