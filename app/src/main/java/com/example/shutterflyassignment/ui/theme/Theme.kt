package com.example.shutterflyassignment.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

fun Modifier.gradientBackground() = this.then(
    Modifier.background(
        brush = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF1F1A17),  // Dark color at the top
                Color(0xFF2A1D14),  // Middle color
                Color(0xFF582F11)   // Lightest color at the bottom
            )
        )
    )
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    background = DarkBackground,
    surface = LightBackground,
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun ShutterflyAssignmentTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}