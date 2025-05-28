package com.wooze.wear.woodfish.presentation.ui.components.mainpage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Switch
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import com.wooze.wear.woodfish.R

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
