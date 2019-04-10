package com.hj.espressotest

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.InstrumentationRegistry.getTargetContext
import android.content.ComponentName
import android.content.Intent
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.intent.Intents


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)


    companion object {
        private const val CORRECT_ID = "link"
        private const val CORRECT_PASSWORD = "SamsungDev1!"

        private const val INVALID_ID = "link2"
        private const val INVALID_PASSWORD = "HPDev1!"
    }


    @Test
    fun button_Disable_Test() {
        onView(withId(R.id.btnLogin)).check(matches(not(isEnabled())))
    }

    @Test
    fun button_Disable_Test2() {
        onView(withId(R.id.etUserID)).perform(ViewActions.typeText(CORRECT_ID), closeSoftKeyboard())
        onView(withId(R.id.btnLogin)).check(matches(not(isEnabled())))
    }

    @Test
    fun button_Disable_Test3() {
        onView(withId(R.id.etUserID)).perform(ViewActions.typeText(CORRECT_ID), closeSoftKeyboard())
        onView(withId(R.id.etPassword)).perform(ViewActions.typeText(CORRECT_PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.btnLogin)).check(matches((isEnabled())))
    }


    @Test
    fun  correctLogin() {
        onView(withId(R.id.etUserID)).perform(ViewActions.typeText(CORRECT_ID), closeSoftKeyboard())
        onView(withId(R.id.etPassword)).perform(ViewActions.typeText(CORRECT_PASSWORD), closeSoftKeyboard())

        Intents.init()
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withText("Login Success")).inRoot(withDecorView(not(rule.activity.window.decorView))).check(matches(isDisplayed()))
        intended(hasComponent(SuccessActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun  invalidLogin() {
        onView(withId(R.id.etUserID)).perform(ViewActions.typeText(INVALID_ID), closeSoftKeyboard())
        onView(withId(R.id.etPassword)).perform(ViewActions.typeText(INVALID_PASSWORD), closeSoftKeyboard())

        onView(withId(R.id.btnLogin)).perform(click())
        onView(withText("Login Fail")).inRoot(withDecorView(not(rule.activity.window.decorView))).check(matches(isDisplayed()))
    }


}