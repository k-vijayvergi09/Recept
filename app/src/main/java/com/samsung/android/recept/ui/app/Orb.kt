package com.samsung.android.recept.ui.app

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.android.recept.R

// ─────────────────────────────────────────────────────────────────────────────
// Colour palette (Idle Only)
// ─────────────────────────────────────────────────────────────────────────────
private val OrbVioletCore = Color(0xFF7C3AED)
private val OrbVioletDeep = Color(0xFF4C1D95)

private val DarkInnerGlow = Color(0xFF8B5CF6).copy(alpha = 0.25f)
private val DarkOuterGlow = Color(0xFF8B5CF6).copy(alpha = 0.12f)
private val DarkGlowIdle = Color(0xFF6D28D9).copy(alpha = 0.35f)

private val LightInnerGlow = Color(0xFF6D28D9).copy(alpha = 0.18f)
private val LightOuterGlow = Color(0xFF6D28D9).copy(alpha = 0.08f)
private val LightGlowIdle = Color(0xFF6D28D9).copy(alpha = 0.25f)

// ─────────────────────────────────────────────────────────────────────────────
// Animation constants (Breathing Only)
// ─────────────────────────────────────────────────────────────────────────────
private const val BREATH_MIN = 0.96f
private const val BREATH_MAX = 1.06f
private const val BREATH_MS = 750

// ─────────────────────────────────────────────────────────────────────────────
// DrawScope helpers
// ─────────────────────────────────────────────────────────────────────────────
private fun DrawScope.drawRadialBloom(center: Offset, radius: Float, color: Color) {
    drawCircle(
        brush = Brush.radialGradient(
            colorStops = arrayOf(
                0.00f to color,
                0.70f to color.copy(alpha = 0f),
                1.00f to Color.Transparent,
            ),
            center = center,
            radius = radius,
        ),
        radius = radius,
        center = center,
    )
}

private fun DrawScope.drawOrbCore(center: Offset, radius: Float, coreStart: Color, coreEnd: Color) {
    val w = radius * 2
    val h = radius * 2
    val left = center.x - radius
    val top = center.y - radius
    drawOval(
        brush = Brush.linearGradient(
            colors = listOf(coreStart, coreEnd),
            start = Offset(left + w * 0.15f, top + h * 0.10f),
            end = Offset(left + w * 0.85f, top + h * 0.90f),
        ),
        topLeft = Offset(left, top),
        size = Size(w, h),
    )
}

private fun DrawScope.drawGlowBloom(center: Offset, orbRadius: Float, color: Color) {
    drawRadialBloom(center, orbRadius * 1.6f, color.copy(alpha = color.alpha * 0.8f))
    drawRadialBloom(center, orbRadius * 2.8f, color.copy(alpha = color.alpha * 0.3f))
}

// ─────────────────────────────────────────────────────────────────────────────
// Main Idle Composable
// ─────────────────────────────────────────────────────────────────────────────
@Preview
@Composable
fun SeamlessOrbIdle(
    modifier: Modifier = Modifier,
    orbSize: Dp = 64.dp,
    darkTheme: Boolean = true,
) {
    val innerGlowColor = if (darkTheme) DarkInnerGlow else LightInnerGlow
    val outerGlowColor = if (darkTheme) DarkOuterGlow else LightOuterGlow
    val glowColor = if (darkTheme) DarkGlowIdle else LightGlowIdle

    val breathTransition = rememberInfiniteTransition(label = "breath")
    val breathScale by breathTransition.animateFloat(
        initialValue = BREATH_MIN,
        targetValue = BREATH_MAX,
        animationSpec = infiniteRepeatable(
            animation = tween(BREATH_MS, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "breathScale",
    )

    val canvasSizeDp = orbSize * 2.2f

    Box(
        modifier = modifier.size(canvasSizeDp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val orbRadius = orbSize.toPx() / 2f

            // Layer 1 & 2: Glow Blooms
            drawRadialBloom(center, orbRadius * breathScale * 2.4f, outerGlowColor)
            drawRadialBloom(center, orbRadius * breathScale * 1.6f, innerGlowColor)
            drawGlowBloom(center, orbRadius * breathScale, glowColor)

            // Layer 3: Orb core (pure circle, no blob distortion)
            drawOrbCore(center, orbRadius * breathScale, OrbVioletCore, OrbVioletDeep)

            // Layer 4: White dot highlight
        }

        Image(
            painter = painterResource(R.drawable.baseline_mic_24),
            contentDescription = "Orb Center Image",
            modifier = Modifier
                .size(orbSize * 0.45f) // Makes the image take up 45% of the inner orb's diameter
                .graphicsLayer {
                    // Apply the exact same scale so it breathes with the orb
                    scaleX = breathScale
                    scaleY = breathScale
                }
        )
    }
}


@Preview
@Composable
fun SeamlessOrbPillIdle(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = true,
) {
    val blinkTransition = rememberInfiniteTransition(label = "pillBlink")
    val blinkAlpha by blinkTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "blinkAlpha",
    )

    val pillBg =
        if (darkTheme) Color(0xFF0A0812).copy(alpha = 0.65f) else Color(0xFFFFFFFF).copy(alpha = 0.82f)
    val pillBorder = Color(0xFF6D28D9).copy(alpha = 0.25f)
    val textColor =
        if (darkTheme) Color(0xFFFFFFFF).copy(alpha = 0.35f) else Color(0xFF110826).copy(alpha = 0.45f)
    val dotColor = Color(0xFF7C3AED).copy(alpha = blinkAlpha * 0.6f)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(pillBg)
            .border(1.dp, pillBorder, RoundedCornerShape(50))
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Box(Modifier
            .size(4.dp)
            .background(dotColor, RoundedCornerShape(50)))
        Text(
            text = "say hey seamless",
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.12.sp,
        )
    }
}
