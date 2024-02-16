package com.shady.currencyconversionapp.presentation.model

sealed class CurrencyIntent{
    data object GetCurrency : CurrencyIntent()
    data class Calculate(val rate: Float, val currentValue: String) : CurrencyIntent()
}
