package com.wooze.wear.woodfish.presentation.ui.components.mainpage

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

fun vibrate(context: Context) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        // 对于 Android 12 以上
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        // 安卓11
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    val vibrationEffect =
        VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)

    vibrator.vibrate(vibrationEffect)
}