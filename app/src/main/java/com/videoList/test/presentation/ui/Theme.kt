package com.videoList.test.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val DarkColorPalette = darkColorScheme(
        primary = Purple200,
        onPrimary = Purple700,
        secondary = Teal200
    )

    private val LightColorPalette = lightColorScheme(
        primary = Purple500,
        onPrimary = Purple700,
        secondary = Teal200)


@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = QuickSandTypography,
        shapes = AppShapes
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Grey1)
        ){
            content()
        }
    }
}
