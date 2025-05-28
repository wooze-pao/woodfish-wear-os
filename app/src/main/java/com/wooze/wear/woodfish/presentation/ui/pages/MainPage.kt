package com.wooze.wear.woodfish.presentation.ui.pages


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.InlineSlider
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.wooze.wear.woodfish.R
import com.wooze.wear.woodfish.presentation.data.MainViewModel
import com.wooze.wear.woodfish.presentation.ui.components.mainpage.VibrateToggle

@Composable
fun MainPage(
    viewModel: MainViewModel,
    listState: ScalingLazyListState,
    navController: NavController,
) {
    val newText by viewModel.newText
    val selectedColor by viewModel.selectedColor
    val selectedSound by viewModel.selectedSoundEffect
    val isVibrateOpen by viewModel.isVibrateOpen

    Scaffold(
        timeText = { TimeText() },
        content = {
            ScalingLazyColumn(state = listState) {
                item {
                    MuyuItemPage(
                        viewModel,
                        newText,
                        selectedColor,
                        selectedSound,
                        isVibrateOpen
                    )
                }
                item {
                    Chip(
                        label = { Text("自定义") },
                        onClick = { navController.navigate("muyuCustomPage") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp),
                        icon = {
                            Icon(
                                painterResource(R.drawable.ic_chip_edit),
                                contentDescription = "自定义",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        secondaryLabel = { Text("自定义木鱼样式") }
                    )
                }
                item {
                    InlineSlider(
                        value = viewModel.volumeLevel.value,
                        onValueChange = { viewModel.updateVolumeLevel(it) },
                        increaseIcon = {
                            Icon(
                                painterResource(R.drawable.ic_sound_max),
                                "Increase"
                            )
                        },
                        decreaseIcon = {
                            Icon(
                                painterResource(R.drawable.ic_sound_min),
                                "Decrease"
                            )
                        },
                        valueProgression = 0..4,
                        segmented = true
                    )
                }
                item {
                    VibrateToggle(
                        isVibrateOpen,
                        onCheckedChange = { boolean -> viewModel.updateIsVibrateOpen(boolean) })
                }
                item {
                    Text(
                        "感谢下载本软件\n版本" + stringResource(R.string.app_version),
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            }
        },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    )


}