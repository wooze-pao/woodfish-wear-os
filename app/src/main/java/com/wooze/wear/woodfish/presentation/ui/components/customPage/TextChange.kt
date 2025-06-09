package com.wooze.wear.woodfish.presentation.ui.components.customPage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.wooze.wear.woodfish.R
import com.wooze.wear.woodfish.presentation.ui.NavRoutes

@Composable
fun TextChange(
    navController: NavController, text: String
) {
    Chip(
        onClick = { navController.navigate(NavRoutes.TEXT_CHANGE_PAGE) },
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