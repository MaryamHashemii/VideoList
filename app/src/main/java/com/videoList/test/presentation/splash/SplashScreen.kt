package com.videoList.test.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.videoList.test.R
import com.videoList.test.core.util.TestTags.SPLASH
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    onNavigate: () -> Unit, lifecycleOwner: LifecycleOwner
) {
    Image(
        painterResource(R.drawable.logo_aparat),
        contentDescription = "SPLASH",
        modifier = Modifier
            .fillMaxSize()
            .testTag(SPLASH)

    )
    lifecycleOwner.lifecycleScope.launch {
        delay(2000)
        onNavigate()
    }
}
