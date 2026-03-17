package com.samsung.android.recept.ui.onboarding

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.android.recept.ui.theme.AppTypography
import androidx.core.net.toUri
import com.samsung.android.recept.utils.Constants

@Preview
@Composable
fun DrawOverAppsPage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val canDrawOverlays = Settings.canDrawOverlays(context)

    if (!canDrawOverlays) {
        context.startActivity(
            Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                "package:${context.packageName}".toUri()
            )
        )
        context.findActivity()!!.intent.putExtra(Constants.EXTRA_WAITING_FOR_PERMISSION_RESULT, true)
    }
    Column(modifier = modifier.fillMaxSize().padding(top = 10.dp)) {
        Text(text = "Draw Over Apps", style = AppTypography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "This is what lets the app float over everything -- the overlay that helps make the app-less experience possible.",
            style = AppTypography.bodyMedium
        )
    }
}