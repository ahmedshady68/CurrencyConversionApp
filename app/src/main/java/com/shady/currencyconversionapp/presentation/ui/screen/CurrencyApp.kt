package com.shady.currencyconversionapp.presentation.ui.screen

import androidx.compose.runtime.Composable
import com.shady.currencyconversionapp.presentation.model.CurrencyViewState

@Composable
fun CurrencyApp(
    currencyList: CurrencyViewState?,
    retryOnClick: () -> Unit,
    calculateOnClick: (Float, String) -> Unit,
) {
    currencyList?.isLoading.also { showLoading ->
        CurrencyLoadingScreen(showLoading)
    }
    currencyList?.currencyAppModel?.let {
        CurrencySuccessScreen(state = currencyList, calculateOnClick = calculateOnClick)
    }
    currencyList?.error?.let {
        CurrencyErrorScreen(retryOnClick = retryOnClick)
    }
}