package com.wooze.wear.woodfish.presentation.data

import android.media.AudioAttributes
import android.media.SoundPool

fun ReUseSoundPool(): SoundPool {
    return SoundPool.Builder().setMaxStreams(2).setAudioAttributes(
        AudioAttributes.Builder().setUsage(
            AudioAttributes.USAGE_MEDIA
        ).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
    ).build()
}