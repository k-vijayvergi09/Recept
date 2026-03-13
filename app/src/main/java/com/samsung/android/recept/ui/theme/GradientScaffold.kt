package com.samsung.android.recept.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GradientScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackBarHost: @Composable () -> Unit = {}, // Fix 1: Takes no arguments
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier.gradientBackground(),
        // Make the scaffold background transparent so our gradient shows through
        containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackBarHost, // Fix 1: Passed directly
        floatingActionButton = floatingActionButton
    ) { innerPadding ->
        // Fix 2: Pass innerPadding directly to content (not as a Modifier)
        content(innerPadding)
    }
}

@Preview
@Composable
fun Preview() {
    GradientScaffold { }
}