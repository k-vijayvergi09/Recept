package com.samsung.android.recept.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Colours ─────────────────────────────────────────────────
private val FeatureBg     = Color(0x0AFFFFFF)   // rgba(255,255,255,0.04)
private val FeatureBorder = Color(0x12FFFFFF)   // rgba(255,255,255,0.07)
private val FeatureText   = Color(0x8CFFFFFF)   // rgba(255,255,255,0.55)

// ── Single row ────────────────────────────────────────────────────────────────
@Composable
fun FeatureItem(
    icon:     String,
    label:    String,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(16.dp)   // rounded-2xl

    Row(
        verticalAlignment    = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),   // gap-3
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(FeatureBg)
            .border(1.dp, FeatureBorder, shape)
            .padding(horizontal = 16.dp, vertical = 12.dp),    // px-4 py-3
    ) {
        Text(
            text     = icon,
            fontSize = 18.sp,                                   // text-lg
        )
        Text(
            text       = label,
            color      = FeatureText,
            fontSize   = 14.sp,                                 // text-sm
            fontWeight = FontWeight.Light,                      // font-light = 300
        )
    }
}

// ── All three feature rows ───────────────────
@Composable
fun FeatureList(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),     // gap-2.5
        modifier = modifier.fillMaxWidth(),
    ) {
        FeatureItem(
            icon  = "🎙️",
            label = "Wake word activation — say Hey Seamless",
        )
        FeatureItem(
            icon  = "📋",
            label = "Auto-transcribes and copies your speech",
        )
        FeatureItem(
            icon  = "⚡",
            label = "Executes actions across your apps by voice",
        )
    }
}