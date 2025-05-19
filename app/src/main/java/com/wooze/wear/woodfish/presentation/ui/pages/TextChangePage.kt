package com.wooze.wear.woodfish.presentation.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.ui.text.input.ImeAction
import com.wooze.wear.woodfish.presentation.data.MainViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TextChangePage(
    viewModel: MainViewModel,
    navController: NavController
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var hasStarted by remember { mutableStateOf(false) }
    var hasOpenedIme by remember { mutableStateOf(false) }
    val imeVisible = WindowInsets.isImeVisible
    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
        keyboardController?.show()
        hasStarted = true
    }

    LaunchedEffect(imeVisible) {
        if (imeVisible && hasStarted) {
            hasOpenedIme = true
        } else if (hasOpenedIme && hasStarted) {
            hasOpenedIme = false
            hasStarted = false
            focusRequester.freeFocus()
            navController.navigate("mainPage")
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        BasicTextField(
            value = viewModel.newText.value,
            onValueChange = { text -> viewModel.updateText(text) },
            modifier = Modifier
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                navController.navigate("mainPage")
            },)
        )
    }
}

