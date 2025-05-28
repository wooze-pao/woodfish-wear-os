package com.wooze.wear.woodfish.presentation.ui.components.customPage

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.CompactChip
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

@Composable
fun SoundChange(soundEffect: String, onSoundChange: (String) -> Unit) {
    Card(onClick = {}) {
        Column {
            Text("音效")
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .horizontalScroll(scrollState),
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
                            if (label == soundEffect) {
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

