package com.shady.currencyconversionapp.presentation.model

import com.shady.domain.entity.Rates

data class CurrencyAppModel(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: Rates?,
    val timestamp: Int?,
)