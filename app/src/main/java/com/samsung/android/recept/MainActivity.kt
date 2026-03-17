package com.samsung.android.recept

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samsung.android.recept.ui.app.ReceptApp
import com.samsung.android.recept.ui.onboarding.AskPermissionPage
import com.samsung.android.recept.ui.onboarding.DotPillPagerIndicator
import com.samsung.android.recept.ui.onboarding.DrawOverAppsPage
import com.samsung.android.recept.ui.onboarding.GetStartedPage
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

                   Column(modifier = modifier.fillMaxSize()) {
                        Row(modifier = Modifier.height(40.dp).fillMaxWidth().padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            DotPillPagerIndicator(pagerState = pagerState, modifier = Modifier.height(40.dp))
                            Text(text = "${pagerState.currentPage + 1} of ${pagerState.pageCount}")
                        }
                       HorizontalPager(state = pagerState, modifier = modifier.fillMaxWidth().weight(1f)) { page ->
                           when (page) {
                               0 -> GetStartedPage(modifier) {
                                   coroutineScope.launch {
                                       pagerState.animateScrollToPage(1)
                                   }
                               }
                               1-> AskPermissionPage()
                               2-> DrawOverAppsPage()
                           }
                       }
                   }
                }
            }
        }
    }
}