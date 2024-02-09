package com.shady.currencyconversionapp.presentation.model

import com.shady.domain.entity.CurrencyDomainRates

data class CurrencyAppModel(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: CurrencyDomainRates?,
    val timestamp: Int?,
)