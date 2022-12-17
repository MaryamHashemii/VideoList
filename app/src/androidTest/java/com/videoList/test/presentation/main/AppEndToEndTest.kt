package com.videoList.test.presentation.main

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.videoList.test.core.util.TestTags
import com.videoList.test.core.util.TestTags.SPLASH
import com.videoList.test.core.util.TestTags.VIDEO_DETAIL
import com.videoList.test.core.util.TestTags.VIDEO_IMAGE
import com.videoList.test.core.util.TestTags.VIDEO_ITEM
import com.videoList.test.core.util.TestTags.VIDEO_List
import com.videoList.test.core.util.TestTags.VIDEO_TITLE
import com.videoList.test.core.util.TestTags.VIDEO_VSIT_COUNT
import com.videoList.test.di.AppModule
import com.videoList.test.di.DatabaseModule
import com.videoList.test.di.NetworkModule
import com.videoList.test.di.RepositoryModule
import com.videoList.test.presentation.splash.SplashScreen
import com.videoList.test.presentation.ui.AppTheme
import com.videoList.test.presentation.video.VideoScreen
import com.videoList.test.presentation.video_detail.VideoDetailScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(
    AppModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    RepositoryModule::class
)
class AppEndToEndTest {
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
                    startDestination = Route.Splash.route
                ) {
                    composable(Route.Splash.route) {
                        SplashScreen(
                            onNavigate = {
                                navController.navigate(route = Route.VideoList.route) {
                                    launchSingleTop = true
                                }
                            },
                            lifecycleOwner = composeRule.activity
                        )
                    }
                    composable(Route.VideoList.route) {
                        VideoScreen(
                            onNavigate = {
                                navController.navigate(route = Route.VideoDetail.route + "?videoId=" + it.id) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                    composable(route = Route.VideoDetail.route + "?videoId={videoId}",
                        arguments = listOf(
                            navArgument(
                                name = "videoId"
                            ) {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )) {
                        VideoDetailScreen()
                    }
                }
            }
        }
    }

    @Test
    fun checkTheFlow(): Unit = runBlocking {
        composeRule.onNodeWithTag(SPLASH).assertIsDisplayed()
        delay(2000)
        composeRule.onAllNodesWithTag(VIDEO_List)[0].assertIsDisplayed()
        composeRule.onAllNodesWithTag(VIDEO_ITEM)[0].performClick()
        composeRule.onNodeWithTag(VIDEO_DETAIL).onChildAt(0).assertIsDisplayed()
        composeRule.onNodeWithTag(VIDEO_DETAIL).onChildAt(1).assertIsDisplayed()
        composeRule.onNodeWithTag(VIDEO_DETAIL).onChildAt(2).assertIsDisplayed()

    }
}