package com.samsung.android.recept.ui.app

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.samsung.android.recept.ui.theme.GradientScaffold

@Composable
fun ReceptApp(modifier: Modifier = Modifier, content: @Composable (modifier: Modifier) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    GradientScaffold(modifier, snackBarHost = {
        SnackbarHost(snackbarHostState)
    }) {
            content(modifier)
    }
}