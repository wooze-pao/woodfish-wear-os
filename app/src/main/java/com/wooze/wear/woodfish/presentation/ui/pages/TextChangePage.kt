package com.wooze.wear.woodfish.presentation.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.text.BasicSecureTextField
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
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
    var newValue by remember {
        mutableStateOf(
            TextFieldValue(
                viewModel.newText.value
            )
        )
    }
    LaunchedEffect(Unit) {
        delay(150)
        focusRequester.requestFocus()
        println("TextChangePage ${newValue}")
        hasStarted = true
    }

    LaunchedEffect(imeVisible) {
        if (imeVisible && hasStarted) {
            hasOpenedIme = true
        } else if (hasOpenedIme && hasStarted) {
            hasOpenedIme = false
            hasStarted = false
            navController.popBackStack()
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        BasicTextField(
            value = newValue,
            onValueChange = { value ->
                newValue = value
                println("TextChangePage ${newValue}")
                viewModel.updateText(value.text)
            },
            modifier = Modifier
                .focusRequester(focusRequester) ,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    navController.popBackStack()
                },
            )
        )


    }
}

