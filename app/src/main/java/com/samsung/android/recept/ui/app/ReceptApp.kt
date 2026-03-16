package com.samsung.android.recept.ui.app

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samsung.android.recept.ui.theme.GradientScaffold

@Composable
fun ReceptApp(modifier: Modifier = Modifier, content: @Composable (modifier: Modifier, navController: NavHostController) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    GradientScaffold(modifier, snackBarHost = {
        SnackbarHost(snackbarHostState)
    }) {
            content(modifier, navController)
    }
}