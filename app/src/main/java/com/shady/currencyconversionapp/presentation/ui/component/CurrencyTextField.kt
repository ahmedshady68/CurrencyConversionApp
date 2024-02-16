package com.shady.currencyconversionapp.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun CurrencyTextFieldWithButton(currencyValue: TextFieldValue) {
    Row {
        GetCurrencyTextField()
        // Spacer(modifier = Modifier.padding(20.dp))
         // GetCurrencyButton(currencyValue)
    }
}

@Composable
fun GetCurrencyTextField() {
    var currencyValue by remember { mutableStateOf(TextFieldValue("")) }
    Row {
    TextField(
        value = currencyValue,
        onValueChange = { newText ->
            currencyValue = newText
        },
        label = { Text(text = "enter value..") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
        // GetCurrencyButton()
    }
}

@Composable
fun GetCurrencyButton(calculateOnClick: () -> Unit) {
    Button(onClick = {
        calculateOnClick.invoke()
    }) {
        Text(text = "Get")
    }
}