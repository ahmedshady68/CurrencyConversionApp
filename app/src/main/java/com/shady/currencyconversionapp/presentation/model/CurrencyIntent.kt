package com.shady.currencyconversionapp.presentation.model

sealed class CurrencyIntent{
    data object GetCurrency : CurrencyIntent()
}
