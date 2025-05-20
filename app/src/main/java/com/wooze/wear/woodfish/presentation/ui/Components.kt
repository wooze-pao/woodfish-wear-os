package com.wooze.wear.woodfish.presentation.ui

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.AnchorType
import androidx.wear.compose.foundation.CurvedAlignment
import androidx.wear.compose.foundation.CurvedDirection
import androidx.wear.compose.foundation.CurvedLayout
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.CompactChip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Switch
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleButton
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.ToggleChipDefaults
import androidx.wear.compose.material.curvedText
import com.wooze.wear.woodfish.R


@Composable
fun SoundChange(soundEffect: String, onSoundChange: (String) -> Unit) {
    Card(onClick = {}) {
        Column {
            Text("音效")
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier.horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                listOf("清脆", "沉重", "坤叫", "Ciallo～(∠·ω< )⌒★").forEach { label ->
                    CompactChip(
                        onClick = { onSoundChange(label) },
                        label = {
                            Text(
                                label,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis, fontSize = 12.sp
                            )
                        }, colors = ChipDefaults.chipColors(
                            if (label === soundEffect) {
                                MaterialTheme.colors.primary
                            } else {
                                MaterialTheme.colors.surface
                            }
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ColorChange(colorV: Color, onColorChange: (Color) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Chip(
            onClick = { expanded = !expanded },
            label = { Text("颜色") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            icon = {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = colorV,
                            shape = CircleShape
                        )
                        .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                )
            },
            secondaryLabel = { Text("用于调整木鱼的颜色") }
        )

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    Color.White,
                    Color.Red,
                    Color.Green,
                    Color.Blue,
                    Color.Yellow
                ).forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(color, shape = CircleShape)
                            .border(
                                width = 2.dp,
                                color = if (color == colorV) Color.White else Color.Gray,
                                shape = CircleShape
                            )
                            .clickable {
                                onColorChange(color)
                                expanded = false // 收起
                            }
                    )
                }
            }
        }

    }
}

@Composable
fun TextChange(
    navController: NavController, text: String
) {
    Chip(
        onClick = { navController.navigate("textChangePage") },
        label = { Text("更改文字") },
        modifier = Modifier.fillMaxWidth(),
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_chip_rename),
                contentDescription = "更改文字",
                modifier = Modifier.size(24.dp)
            )
        }, secondaryLabel = { Text("当前文字：${text}+1") }
    )
}

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

@Composable
fun VibrateToggle(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {

    ToggleChip(checked = checked,
        label = { Text("震动开关") },
        toggleControl = { Switch(checked = checked) },
        onCheckedChange = onCheckedChange,
        modifier = Modifier.fillMaxWidth(),
        appIcon = {Icon(painter = painterResource(R.drawable.ic_chip_vibration),contentDescription = null)}
    )
}