package com.samsung.android.recept.ui.onboarding

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun DotPillPagerIndicator(modifier: Modifier = Modifier, pagerState: PagerState) {
    val dotSize = 8.dp
    val activeWidth = 22.dp
    val spacing = 10.dp
    val inactiveColor: Color = Color(0xFFCBD5E1)
    val activeColor: Color = Color(0xFF7c3aed)

    if (pagerState.pageCount == 0) return

    val totalWidth = activeWidth + (pagerState.pageCount - 1) * (dotSize + spacing)

    Canvas(
        modifier = modifier
            .width(totalWidth)
            .height(dotSize)
    ) {
        val dotPx = dotSize.toPx()
        val activePx = activeWidth.toPx()
        val spacingPx = spacing.toPx()
        val stepPx = dotPx + spacingPx
        val radius = dotPx / 2f
        val centerY = size.height / 2f

        val centers = List(pagerState.pageCount) { index ->
            activePx / 2f + index * stepPx
        }

        centers.forEach { x ->
            drawCircle(
                color = inactiveColor,
                radius = radius,
                center = Offset(x, centerY)
            )
        }

        val position = (pagerState.currentPage + pagerState.currentPageOffsetFraction)
            .coerceIn(0f, (pagerState.pageCount - 1).toFloat())

        val from = floor(position).toInt()
        val to = ceil(position).toInt().coerceIn(0, pagerState.pageCount - 1)
        val progress = position - from

        val fromCenter = centers[from]
        val toCenter = centers[to]

        val fromLeft = fromCenter - activePx / 2f
        val fromRight = fromCenter + activePx / 2f
        val toLeft = toCenter - activePx / 2f
        val toRight = toCenter + activePx / 2f

        val head = FastOutSlowInEasing.transform((progress * 2f).coerceIn(0f, 1f))
        val tail = FastOutSlowInEasing.transform(((progress - 0.5f) * 2f).coerceIn(0f, 1f))

        val movingForward = to >= from

        val left = if (movingForward) {
            lerpFloat(fromLeft, toLeft, tail)
        } else {
            lerpFloat(fromLeft, toLeft, head)
        }

        val right = if (movingForward) {
            lerpFloat(fromRight, toRight, head)
        } else {
            lerpFloat(fromRight, toRight, tail)
        }

        drawRoundRect(
            color = activeColor,
            topLeft = Offset(left, centerY - radius),
            size = Size(right - left, dotPx),
            cornerRadius = CornerRadius(radius, radius)
        )
    }
}

private fun lerpFloat(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}