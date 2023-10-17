package com.example.assign4_pbm5_hangman

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun progressVariablesInitialized() {
        var newViewModel = MainActivityViewModel()
        assertEquals(newViewModel.timeToRemoveHalf, false)
        assertEquals(newViewModel.timeToRemoveVowels, false)
        assertEquals(newViewModel.wonTheGame, false)
        assertEquals(newViewModel.lostTheGame, false)

    }

    @Test
    fun allPossibleWordsAreCorrectLength() {
        var newViewModel = MainActivityViewModel()
        for (word in newViewModel.Words) {
            assertEquals(word.length, 6)
        }
    }
}