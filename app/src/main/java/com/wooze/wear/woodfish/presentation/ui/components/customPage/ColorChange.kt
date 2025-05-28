package com.wooze.wear.woodfish.presentation.ui.components.customPage

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
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Text

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

