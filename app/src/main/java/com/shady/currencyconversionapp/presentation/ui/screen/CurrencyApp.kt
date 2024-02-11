package com.shady.currencyconversionapp.presentation.ui.screen

import androidx.compose.runtime.Composable
import com.shady.currencyconversionapp.presentation.model.CurrencyViewState

@Composable
fun CurrencyApp(currencyList: CurrencyViewState?, retryOnClick: () -> Unit) {
    currencyList?.isLoading.also { showLoading ->
        CurrencyLoadingScreen(showLoading)
    }
    currencyList?.currencyAppModel?.let {
        CurrencySuccessScreen(state = currencyList)
    }
    currencyList?.error?.let {
        CurrencyErrorScreen(retryOnClick = retryOnClick)
    }
}