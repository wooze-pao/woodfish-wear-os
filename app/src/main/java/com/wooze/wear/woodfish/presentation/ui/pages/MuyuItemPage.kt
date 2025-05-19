package com.wooze.wear.woodfish.presentation.ui.pages

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.wooze.wear.woodfish.R
import com.wooze.wear.woodfish.presentation.data.MainViewModel
import com.wooze.wear.woodfish.presentation.ui.vibrate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MuyuItemPage(
    viewModel: MainViewModel,
    text: String,
    color: Color,
    sound: String
) {
    val countNumber by viewModel.countNumber
    val context = LocalContext.current
    val animationTime = 100
    val coroutineScope = rememberCoroutineScope()

    val soundPool = remember {
        SoundPool.Builder().setMaxStreams(2).setAudioAttributes(
            AudioAttributes.Builder().setUsage(
                AudioAttributes.USAGE_MEDIA
            ).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        ).build()
    }
    val soundId = remember(context, soundPool, sound) {
        soundPool.load(context, viewModel.soundEffectFileId(), 1)
    }

    DisposableEffect(soundPool) {
        onDispose {
            soundPool.release()
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        Text(text = "${text}+${countNumber}")
        var toggled by remember {
            mutableStateOf(false)
        }
        val scale by animateFloatAsState(
            targetValue = if (!toggled) 1f else 0.8f,
            animationSpec = tween(durationMillis = animationTime),
            label = "scaleAnimation"
        )


        Image(
            painter = painterResource(id = R.drawable.muyu_removebg),
            colorFilter = ColorFilter.tint(color),
            contentDescription = "电子木鱼图片",
            modifier = Modifier
                .scale(scale)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    toggled = true
                    coroutineScope.launch {
                        launch { vibrate(context) }
                        launch { soundPool.play(soundId, 1f, 1f, 0, 0, 1.0f) }
                        viewModel.addCountNumber()
                        delay(timeMillis = animationTime.toLong())
                        toggled = false
                    }
                }
        )

    }
}