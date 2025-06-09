package com.wooze.wear.woodfish.presentation.data

import android.content.Context
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager


fun ReUseVibrator(context: Context): Vibrator {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
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