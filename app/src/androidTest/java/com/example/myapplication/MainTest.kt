package com.example.myapplication

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

//    @get:Rule
//    val activityRule = ActivityScenarioRule(WelcomeActivity::class.java) // Replace with your activity

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity2::class.java)
    //    typeText("abc")
    @Test
    fun loginTest() {
        runBlocking {
            delay(1000L)
            onView(withText("Button"))
                .perform(ViewActions.click())
            delay(1000L)
            composeTestRule.onNodeWithTag("ffff").performClick()
//            delay(1000L)
            composeTestRule.onNodeWithTag("ff").performTextInput("dfasdfad")
            composeTestRule.waitForIdle()
//            onView(withText("Hello"))
//                .perform(ViewActions.click())
            delay(5000L)

        }
    }
}