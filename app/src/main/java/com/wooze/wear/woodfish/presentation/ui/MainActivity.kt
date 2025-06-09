package com.wooze.wear.woodfish.presentation.ui

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = NavRoutes.MAIN_PAGE
    ) {
        composable(NavRoutes.MAIN_PAGE) {
            MainPage(viewModel, navController)
        }

        composable(NavRoutes.TEXT_CHANGE_PAGE) { TextChangePage(viewModel, navController) }

        composable(NavRoutes.MUYU_CUSTOM_PAGE) {
            MuyuCustomPage(navController, viewModel)
        }
    }
}




