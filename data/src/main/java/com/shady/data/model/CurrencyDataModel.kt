package com.shady.data.model

import com.shady.domain.entity.Rates

data class CurrencyDataModel(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: Rates?,
    val timestamp: Int?,
)