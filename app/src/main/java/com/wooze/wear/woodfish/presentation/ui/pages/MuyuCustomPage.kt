package com.wooze.wear.woodfish.presentation.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import com.wooze.wear.woodfish.presentation.data.MainViewModel
import com.wooze.wear.woodfish.presentation.ui.components.customPage.ColorChange
import com.wooze.wear.woodfish.presentation.ui.components.customPage.SoundChange
import com.wooze.wear.woodfish.presentation.ui.components.customPage.TextChange

@Composable
fun MuyuCustomPage(
    listState: ScalingLazyListState,
    navController: NavController,
    viewModel: MainViewModel,
) {
    val newText by viewModel.newText
    val selectedColor by viewModel.selectedColor
    val selectedSound by viewModel.selectedSoundEffect
    LaunchedEffect(Unit) {
        listState.scrollToItem(0)
    }

    Scaffold(
        timeText = { TimeText() },
        content = {
            ScalingLazyColumn(state = listState) {

                item {
                    ColorChange(
                        selectedColor,
                        onColorChange = { newColor -> viewModel.updateColor(newColor) })
                }
                item { TextChange(navController, newText) }
                item {
                    SoundChange(
                        selectedSound,
                        onSoundChange = { sound -> viewModel.updateSoundEffect(sound) })
                }
            }
        },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    )


}

