package com.wooze.wear.woodfish.presentation.ui.components.mainpage

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberSoundPool() : SoundPool {
    return remember {
        SoundPool.Builder().setMaxStreams(10).setAudioAttributes(
            AudioAttributes.Builder().setUsage(
                AudioAttributes.USAGE_MEDIA
            ).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        ).build()
    }
}