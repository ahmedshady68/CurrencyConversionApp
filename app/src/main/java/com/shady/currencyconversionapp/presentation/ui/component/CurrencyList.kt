package com.shady.currencyconversionapp.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.shady.currencyconversionapp.R
import com.shady.currencyconversionapp.presentation.model.CurrencyViewState

@Composable
fun CurrencyList(state: CurrencyViewState, calculateOnClick: (Float, String) -> Unit) {
    if (state.currencyAppModel?.rates != null) {
        var currencyTextFieldValue by remember { mutableStateOf("") }
        var currencyCurrentRate by remember { mutableFloatStateOf(state.currencyAppModel.rates[0].value) }
        Column {
            TextField(
                value = currencyTextFieldValue,
                onValueChange = { newText ->
                    currencyTextFieldValue = newText
                    calculateOnClick(currencyCurrentRate, newText)
                },
                label = { Text(text = stringResource(R.string.currency_text_view_title)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Please select the rate.. ",
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterVertically)
                )
                CurrencyDropdownMenu(
                    state,
                    { currencyCurrentRate = it },
                    calculateOnClick,
                    currencyTextFieldValue
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            LazyColumn {
                items(state.currencyAppModel.rates) { item ->
                    CurrencyCard(rate = item)
                }
            }
        }
    }
}