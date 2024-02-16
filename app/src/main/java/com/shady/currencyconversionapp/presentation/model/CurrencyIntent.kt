package com.shady.currencyconversionapp.presentation.model

sealed class CurrencyIntent{
    data object GetInitialCurrencyRates : CurrencyIntent()
    data class CalculateCurrencyRate(val rate: Float, val currentValue: String) : CurrencyIntent()
}
