package com.samsung.android.recept.ui.onboarding

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AudioPermissionRationaleDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text("Audio Permission Required") },
        text = {
            Text("We need to record audio to use the app. Please grant the permission on the next screen.")
        },
        onDismissRequest = onDismiss,
        confirmButton = { Button(onClick = onConfirm) { Text("Confirm") } },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Not Now")
            }
        }
    )
}