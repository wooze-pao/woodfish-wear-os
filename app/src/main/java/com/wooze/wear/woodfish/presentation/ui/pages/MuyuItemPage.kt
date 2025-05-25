package com.wooze.wear.woodfish.presentation.ui.pages

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.wooze.wear.woodfish.R
import com.wooze.wear.woodfish.presentation.data.MainViewModel
import com.wooze.wear.woodfish.presentation.ui.TextAnimation
import com.wooze.wear.woodfish.presentation.ui.vibrate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID


data class PlusOneText(
    val id: String = UUID.randomUUID().toString(),
    val offset: Offset
)

@Composable
fun MuyuItemPage(
    viewModel: MainViewModel,
    text: String,
    color: Color,
    sound: String,
    isVibrateOpen: Boolean
) {
    val countNumber by viewModel.countNumber
    val context = LocalContext.current
    val animationTime = 100
    val textAnimationTime = 1000
    val coroutineScope = rememberCoroutineScope()
    val plusTextList = remember { mutableStateListOf<PlusOneText>() }

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
            targetValue = if (toggled) 0.9f else 1f,
            animationSpec = tween(durationMillis = animationTime),
            label = "scaleAnimation"
        )

        Box(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures {
                        val newPlusText = PlusOneText(offset = it)
                        plusTextList.add(newPlusText)
                        toggled = true
                        coroutineScope.launch {
                            launch {
                                if (isVibrateOpen) {
                                    vibrate(context)
                                }
                            }
                            launch { soundPool.play(soundId, 1f, 1f, 0, 0, 1.0f) }
                            viewModel.addCountNumber()
                            delay(timeMillis = animationTime.toLong())
                            toggled = false
                        }
                        coroutineScope.launch {
                            delay(textAnimationTime.toLong())
                            plusTextList.remove(newPlusText)
                        }
                    }
                }) {

            Image(
                painter = painterResource(id = R.drawable.muyu_removebg),
                colorFilter = ColorFilter.tint(color),
                contentDescription = "电子木鱼图片",
                modifier = Modifier
                    .scale(scale)
            )

            plusTextList.forEach { plusOne ->
                key(plusOne.id) {
                    TextAnimation(plusOne.offset, textAnimationTime,text)
                }

            }


        }

    }
}

