package com.samsung.android.recept.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.android.recept.ui.theme.AppTypography

@Preview
@Composable
fun DrawOverAppsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().padding(top = 10.dp)) {
        Text(text = "Draw Over Apps", style = AppTypography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "This is what lets the app float over everything -- the overlay that helps make the appless experience possible.",
            style = AppTypography.bodyMedium
        )
    }
}