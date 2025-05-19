/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.wooze.wear.woodfish.presentation.ui

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.wooze.wear.woodfish.presentation.data.MainViewModel
import com.wooze.wear.woodfish.presentation.ui.pages.MainPage
import com.wooze.wear.woodfish.presentation.ui.pages.TextChangePage
import com.wooze.wear.woodfish.presentation.theme.WoodfishTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()
        setTheme(R.style.Theme_DeviceDefault)
        setContent {
            WoodfishTheme {
                WearApp(mainViewModel)
            }

        }
    }
}

@Composable
fun WearApp(viewModel: MainViewModel) {
    val navController = rememberSwipeDismissableNavController()
    val listState = rememberScalingLazyListState()
    LaunchedEffect(Unit) {
        listState.scrollToItem(0)
    }
    Scaffold(

        timeText = { TimeText() },
        content = {
            SwipeDismissableNavHost(
                navController = navController,
                startDestination = "mainPage"
            ) {
                composable("mainPage") { MainPage(viewModel, listState, navController) }

                composable("textChangePage") { TextChangePage(viewModel, navController) }
            }
        },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    )
}




