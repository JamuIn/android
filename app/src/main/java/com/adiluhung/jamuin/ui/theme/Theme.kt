package com.adiluhung.jamuin.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF0396FE),
    primaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFFE1E5F1),
    tertiary = Color(0xFF45C033),

    surface = Color(0xFFD4EDFF),

    background = Color(0xFFF7F8FD),
    onBackground = Color(0xFF000000),
    error = Color(0xFFD0342C),
    errorContainer = Color(0xFFFFE6E5)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0396FE),
    primaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFFE1E5F1),
    tertiary = Color(0xFF45C033),

    surface = Color(0xFFD4EDFF),

    background = Color(0xFFF7F8FD),
    onBackground = Color(0xFF000000),
    error = Color(0xFFD0342C),
    errorContainer = Color(0xFFFFE6E5)
)

@Composable
fun JamuInTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}