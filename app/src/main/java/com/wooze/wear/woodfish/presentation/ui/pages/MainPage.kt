package com.wooze.wear.woodfish.presentation.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.wooze.wear.woodfish.presentation.ui.ColorChange
import com.wooze.wear.woodfish.presentation.data.MainViewModel
import com.wooze.wear.woodfish.presentation.ui.SoundChange
import com.wooze.wear.woodfish.presentation.ui.TextChange

@Composable
fun MainPage(
    viewModel: MainViewModel,
    listState: ScalingLazyListState,
    navController: NavController
) {
    val newText by viewModel.newText
    val selectedColor by viewModel.selectedColor
    val selectedSound by viewModel.selectedSoundEffect

    ScalingLazyColumn(state = listState) {
        item { MuyuItemPage(viewModel, newText, selectedColor, selectedSound,isVibrateOpen) }
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
        item { Text("感谢下载本软件", color = MaterialTheme.colors.onSecondary) }
    }

}