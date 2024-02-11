package com.shady.currencyconversionapp.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shady.currencyconversionapp.presentation.model.CurrencyViewState
import com.shady.currencyconversionapp.presentation.ui.component.AppBarCurrency
import com.shady.currencyconversionapp.presentation.ui.component.CurrencyList

@Composable
fun CurrencySuccessScreen(state: CurrencyViewState?) {
    state?.currencyAppModel?.let {
        Scaffold(topBar = {
            AppBarCurrency()
        }) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                CurrencyList(state = state)
            }
        }
    }
}