package com.wooze.wear.woodfish.presentation.ui.components.mainpage

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.wear.compose.material.Text

@Composable
fun TextAnimation(offset: Offset, time: Int,text: String) {

    var translationY by remember { mutableFloatStateOf(0f) }
    var alpha by remember { mutableFloatStateOf(1f) }

    LaunchedEffect(offset) {
        translationY = -80f
        alpha = 0f
    }

    val animationTranslationY by animateFloatAsState(
        targetValue = translationY,
        animationSpec = tween(time),
        label = "translationY"
    )

    val animationAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(time),
        label = "alpha"
    )

    Text(
        "${text}+1", color = Color.Red, modifier = Modifier
            .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
            .graphicsLayer(translationY = animationTranslationY, alpha = animationAlpha)
    )
}