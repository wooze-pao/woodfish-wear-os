package com.wooze.wear.woodfish.presentation.ui

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.wooze.wear.woodfish.presentation.data.MainViewModel
import com.wooze.wear.woodfish.presentation.ui.pages.MainPage
import com.wooze.wear.woodfish.presentation.ui.pages.MuyuCustomPage
import com.wooze.wear.woodfish.presentation.ui.pages.TextChangePage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()
        setTheme(R.style.Theme_DeviceDefault)
        setContent {
                WearApp(mainViewModel)
        }
    }
}

@Composable
fun WearApp(viewModel: MainViewModel) {
    val navController = rememberSwipeDismissableNavController()
    val context = LocalContext.current

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "mainPage"
    ) {
        composable("mainPage") {
            val listState = rememberScalingLazyListState()
            MainPage(viewModel, listState, navController,context)
        }

        composable("textChangePage") { TextChangePage(viewModel, navController) }

        composable("muyuCustomPage") {
            val listState = rememberScalingLazyListState()
            MuyuCustomPage(listState, navController, viewModel)
        }
    }
}




