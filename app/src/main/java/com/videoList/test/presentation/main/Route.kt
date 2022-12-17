package com.videoList.test.presentation.main

sealed class Route(val route: String) {
    object Splash : Route("splash")
    object VideoList : Route("videoList")
    object VideoDetail : Route("videoDetail")
}