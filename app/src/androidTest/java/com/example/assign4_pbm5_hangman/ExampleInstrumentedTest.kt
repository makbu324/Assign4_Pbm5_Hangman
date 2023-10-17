package com.example.assign4_pbm5_hangman

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.assign4_pbm5_hangman", appContext.packageName)
    }

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun InstrumentalTest2() { //Test if the text is still the same
        onView(withId(R.id.fragment_container)).perform(click())
        val device = UiDevice.getInstance(getInstrumentation())

        device.setOrientationLeft()
        Thread.sleep(15000);
        onView(withId(R.id.whats_the_word)).check(matches(withText("______")))
    }

    fun InstrumentalTest3() { //test if hint button works
        val device = UiDevice.getInstance(getInstrumentation())
        device.setOrientationLeft()
        onView(withId(R.id.hint_button)).perform(click())
        onView(withId(R.id.hint_button)).perform(click())
        onView(withId(R.id.hint_button)).perform(click())

        device.setOrientationRight()
        Thread.sleep(15000);
        onView(withText("______")).check(doesNotExist())
    }
}

class ExampleInstrumentedTest2 { //second instru test
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.assign4_pbm5_hangman", appContext.packageName)
    }

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    fun InstrumentalTest3() { //test if hint button works
        val device = UiDevice.getInstance(getInstrumentation())
        device.setOrientationLeft()
        onView(withId(R.id.hint_button)).perform(click())
        onView(withId(R.id.hint_button)).perform(click())
        onView(withId(R.id.hint_button)).perform(click())

        device.setOrientationRight()
        Thread.sleep(15000);
        onView(withText("______")).check(doesNotExist())
    }
}