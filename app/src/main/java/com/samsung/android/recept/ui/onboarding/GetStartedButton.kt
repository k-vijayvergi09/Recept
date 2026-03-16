package com.samsung.android.recept.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Colours ───────────────────────────────────────────────────────────────────
private val BtnGradientStart = Color(0xFF6D28D9)   // #6d28d9
private val BtnGradientEnd = Color(0xFF4F46E5)   // #4f46e5
private val BtnGlowColor = Color(0xFF6D28D9).copy(alpha = 0.45f)  // rgba(109,40,217,0.45)

// ── Brush: linear-gradient(135deg, ...) ───────────────────────────────────────
// 135deg = top-left → bottom-right diagonal
private val BtnGradientBrush = Brush.linearGradient(
    colors = listOf(BtnGradientStart, BtnGradientEnd),
    start = Offset(0f, 0f),
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
)

// ── Button ─────────────────────────────────────────────────────────────────────
@Composable
fun GetStartedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(16.dp)   // rounded-2xl

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()                 // w-full
            .height(56.dp)                  // h-14
            // box-shadow: 0 0 30px rgba(109,40,217,0.45) — drawn as radial bloom behind the button
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(BtnGlowColor, Color.Transparent),
                        center = Offset(size.width / 2f, size.height / 2f),
                        radius = 120.dp.toPx(),         // ~30px spread maps to ~60dp radius
                    ),
                    radius = 120.dp.toPx(),
                    center = Offset(size.width / 2f, size.height / 2f),
                )
            }
            .clip(shape)
            .background(BtnGradientBrush)
            .clickable(onClick = onClick),
    ) {
        Text(
            text = "Get Started",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}