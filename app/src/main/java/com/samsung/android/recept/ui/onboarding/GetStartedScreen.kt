package com.samsung.android.recept.ui.onboarding

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.android.recept.ui.app.SeamlessOrbIdle

@Composable
fun GetStartedScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        SeamlessOrbIdle(darkTheme = isSystemInDarkTheme())
        Text(
            text = "Seamless",
            fontSize = 36.sp
        )
        Text(
            text = "Your voice. Done.",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(38.dp))
        FeatureList()
        Spacer(modifier = Modifier.height(28.dp))
        GetStartedButton(onClick = onClick)
    }
}