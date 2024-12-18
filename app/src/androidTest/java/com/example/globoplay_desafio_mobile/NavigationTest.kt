package com.example.globoplay_desafio_mobile

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.globoplay_desafio_mobile.navigation.SetupNavGraph
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.HOME_SCREEN
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.LOADING_STATE_ITEM
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.MY_LIST_NAV_ITEM
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.MY_LIST_SCREEN
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            SetupNavGraph(navController = navController)
        }
    }

    @Test
    fun shouldShowHomeScreenOnStartDestination() {
        composeTestRule
            .onNodeWithTag(HOME_SCREEN)
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowMyList_WhenClickOnMyList() {
        composeTestRule
            .onNodeWithTag(MY_LIST_NAV_ITEM)
            .performClick()
        composeTestRule
            .onNodeWithTag(MY_LIST_SCREEN)
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowLoadingStateIcon_WhenLaunch() {
        composeTestRule
            .onAllNodesWithTag(LOADING_STATE_ITEM)
            .onFirst()
            .assertIsDisplayed()
    }
}