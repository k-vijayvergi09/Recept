package com.samsung.android.recept

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samsung.android.recept.ui.app.AskPermission
import com.samsung.android.recept.ui.app.DrawOverApps
import com.samsung.android.recept.ui.app.GetStarted
import com.samsung.android.recept.ui.app.ReceptApp
import com.samsung.android.recept.ui.onboarding.AskPermissionScreen
import com.samsung.android.recept.ui.onboarding.DrawOverAppsScreen
import com.samsung.android.recept.ui.onboarding.GetStartedScreen
import com.samsung.android.recept.ui.theme.ReceptTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReceptTheme {
               ReceptApp(modifier = Modifier.fillMaxSize()) { modifier, pagerState ->
                   val coroutineScope = rememberCoroutineScope()
                   HorizontalPager(state = pagerState, modifier = modifier.fillMaxSize()) { page ->
                       when (page) {
                           0 -> GetStartedScreen(modifier) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                           }
                           1-> AskPermissionScreen()
                           2-> DrawOverAppsScreen()
                       }
                   }
                }
            }
        }
    }
}