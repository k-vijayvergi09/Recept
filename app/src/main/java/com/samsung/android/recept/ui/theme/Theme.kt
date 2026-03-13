package com.samsung.android.recept.ui.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext

private val darkGradient = Brush.verticalGradient(
    0f   to Color(0xFF0a0812), // top
    0.6f to Color(0xFF0f0a1f), // 60%
    1f   to Color(0xFF07050f)  // bottom
)

/**
 * Applies the dark diagonal gradient (175°) as a background.
 * The content inside is counter‑rotated so it stays upright.
 */
fun Modifier.gradientBackground() = this
    .background(brush = darkGradient)
    .graphicsLayer(rotationZ = 175f)   // rotate the gradient
    .then(
        // Optional inner wrapper that counter‑rotates content
        Modifier
            .graphicsLayer(rotationZ = -175f)
            .fillMaxSize()
    )

private val DarkColorScheme = darkColorScheme(
    primary             = VioletCore,
    onPrimary           = Color(0xFFFFFFFF),
    primaryContainer    = VioletDeep,
    onPrimaryContainer  = Color(0xFFFFFFFF),
    secondary           = GreenSuccess,
    onSecondary         = Color(0xFFFFFFFF),
    secondaryContainer  = GreenDeep,
    onSecondaryContainer= Color(0xFFFFFFFF),
    error               = ErrorRed,
    onError             = Color(0xFFFFFFFF),
    background          = Dark_Background,
    onBackground        = Dark_OnBackground,
    surface             = Dark_Surface,
    onSurface           = Dark_OnBackground,
    surfaceVariant      = Dark_SurfaceOverlay,
    onSurfaceVariant    = Dark_OnBackgroundMid,
    outline             = Dark_SurfaceBorder,
    outlineVariant      = Dark_AgentBubbleBorder,
)
private val LightColorScheme = lightColorScheme(
    primary             = VioletCore,
    onPrimary           = Color(0xFFFFFFFF),
    primaryContainer    = Light_UserBubbleBg,
    onPrimaryContainer  = VioletDeep,
    secondary           = GreenSuccess,
    onSecondary         = Color(0xFFFFFFFF),
    secondaryContainer  = Light_SuccessBubbleBg,
    onSecondaryContainer= GreenDeep,
    error               = ErrorRed,
    onError             = Color(0xFFFFFFFF),
    background          = Light_Background,
    onBackground        = Light_OnBackground,
    surface             = Light_Surface,
    onSurface           = Light_OnBackground,
    surfaceVariant      = Light_SurfaceCard,
    onSurfaceVariant    = Light_OnBackgroundMid,
    outline             = Light_SurfaceBorder,
    outlineVariant      = Light_AgentBubbleBorder,
)

@Composable
fun ReceptTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}