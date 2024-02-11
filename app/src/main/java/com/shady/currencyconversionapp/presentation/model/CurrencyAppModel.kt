package com.shady.currencyconversionapp.presentation.model

import com.shady.domain.entity.Rate

data class CurrencyAppModel(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: List<Rate>?,
    val timestamp: Int?,
)