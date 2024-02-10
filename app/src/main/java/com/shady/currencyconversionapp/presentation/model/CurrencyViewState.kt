package com.shady.currencyconversionapp.presentation.model

data class CurrencyViewState(
    val currencyAppModel: CurrencyAppModel? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)
