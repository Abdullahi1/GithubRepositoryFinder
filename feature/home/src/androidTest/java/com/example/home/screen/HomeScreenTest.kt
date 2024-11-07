package com.example.home.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {


    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun confirm_home_screen_is_loaded_properly_on_display() {
        composeTestRule.setContent {
            Box {
                HomeScreen { }
            }
        }

        composeTestRule.onNodeWithContentDescription(
                "Users icon"
            ).assertExists()

        composeTestRule.onNodeWithContentDescription(
                "Repository icon"
            ).assertExists()
    }

}