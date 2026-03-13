package com.samsung.android.recept.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/** Build a 175° linear gradient brush. The tilt is computed from actual canvas
 *  size so stop positions are exact regardless of screen density.
 *  175° in CSS ≈ direction vector (sin 175°, cos 5°) ≈ (0.087, 0.996).
 *  Gradient line through center: start ~(40% width, 0), end ~(60% width, height). */
fun tilted175Brush(
    colors: List<Color>,
    stops: List<Float>,
    width: Float,
    height: Float,
): Brush = Brush.linearGradient(
    colorStops = stops.zip(colors).map { (s, c) -> s to c }.toTypedArray(),
    start = Offset(width * 0.40f, 0f),
    end   = Offset(width * 0.60f, height),
)