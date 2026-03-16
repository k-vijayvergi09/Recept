package com.samsung.android.recept.ui.onboarding

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.samsung.android.recept.PermissionPreferences
import kotlinx.coroutines.launch

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}


@Composable
fun AskPermissionScreen() {
    val context = LocalContext.current
    val activity = context.findActivity()
    val permission = Manifest.permission.RECORD_AUDIO

    val permissionPrefs = remember { PermissionPreferences(context) }

    val hasRequestedAudioBefore by permissionPrefs.hasRequestedAudio.collectAsState(initial = false)

    var isGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    var showRationaleDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        Log.i("Kartik", "launched effect $activity ${activity?.shouldShowRequestPermissionRationale(permission)}")
        if (!isGranted) {
            showRationaleDialog = activity?.shouldShowRequestPermissionRationale(permission)?: false || !hasRequestedAudioBefore
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            isGranted = granted
        }
    )

    if (showRationaleDialog) {
        AudioPermissionRationaleDialog(
            onConfirm = {
                showRationaleDialog = false
                coroutineScope.launch {
                    permissionPrefs.setHasRequestedAudio(true)
                }
                permissionLauncher.launch(permission)
            },
            onDismiss = { showRationaleDialog = false }
        )
    }

}