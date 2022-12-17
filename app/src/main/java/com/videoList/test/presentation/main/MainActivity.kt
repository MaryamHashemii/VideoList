package com.videoList.test.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.videoList.test.presentation.splash.SplashScreen
import com.videoList.test.presentation.video.VideoScreen
import com.videoList.test.presentation.video_detail.VideoDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyAppNavHost(navController = navController)
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun MyAppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        startDestination: String = Route.Splash.route
    ) {
        NavHost(
            modifier = modifier.semantics {
                testTagsAsResourceId = true
            },
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(Route.Splash.route) {
                SplashScreen(
                    onNavigate = {
                        navController.navigate(route = Route.VideoList.route) {
                            launchSingleTop = true
                        }
                    },
                    lifecycleOwner = this@MainActivity
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



