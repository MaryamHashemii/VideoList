package com.videoList.test.presentation.video

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import com.videoList.test.di.AppModule
import com.videoList.test.presentation.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Rule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.videoList.test.core.util.TestTags
import com.videoList.test.di.DatabaseModule
import com.videoList.test.di.NetworkModule
import com.videoList.test.di.RepositoryModule
import com.videoList.test.presentation.main.Route
import com.videoList.test.presentation.ui.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
@UninstallModules(
    AppModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    RepositoryModule::class
)
class VideoScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            AppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Route.VideoList.route
                ) {
                    composable(route = Route.VideoList.route) {
                        VideoScreen(
                            onNavigate = {})
                    }
                }
            }
        }
    }

    @Test
    fun checkCategoryList_isVisible(): Unit = runBlocking {
        composeRule.onNodeWithTag(TestTags.CATEGORY_List).assertDoesNotExist()
        delay(15000)
        composeRule.onNodeWithTag(TestTags.CATEGORY_List).assertIsDisplayed()

    }

    @Test
    fun checkVideoList_isVisible(): Unit = runBlocking {
        composeRule.onNodeWithTag(TestTags.VIDEO_List).assertDoesNotExist()
        delay(15000)
        composeRule.onNodeWithTag(TestTags.VIDEO_List).assertIsDisplayed()

    }
}