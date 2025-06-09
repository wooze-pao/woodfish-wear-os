package com.wooze.wear.woodfish.presentation.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.wooze.wear.woodfish.presentation.data.MainViewModel
import com.wooze.wear.woodfish.presentation.ui.components.customPage.ColorChange
import com.wooze.wear.woodfish.presentation.ui.components.customPage.SoundChange
import com.wooze.wear.woodfish.presentation.ui.components.customPage.TextChange

@Composable
fun MuyuCustomPage(
    navController: NavController,
    viewModel: MainViewModel,
) {
    val newText by viewModel.newText
    val selectedColor by viewModel.selectedColor
    val selectedSound by viewModel.selectedSoundEffect
    val listState = rememberScalingLazyListState()


    Scaffold(
        timeText = { TimeText() },
        content = {
            ScalingLazyColumn(state = listState) {

                item {
                    Text(
                        "自定义",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(10.dp)
                    )
                }

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

