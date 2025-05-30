package com.wooze.wear.woodfish.presentation.ui.components.mainpage

import android.content.Context
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


@Composable
fun rememberVibrator(context: Context): Vibrator {
    return remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // 对于 Android 12 以上
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            // 安卓11
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }
}