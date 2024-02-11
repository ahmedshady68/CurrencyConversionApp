package com.shady.currencyconversionapp.presentation.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.shady.currencyconversionapp.presentation.model.CurrencyViewState

@Composable
fun CurrencyList(state: CurrencyViewState) {
    if (state.currencyAppModel?.rates != null) {
        LazyColumn {
            items(state.currencyAppModel.rates) { item ->
                CurrencyCard(rate = item)
            }
        }
    }
}