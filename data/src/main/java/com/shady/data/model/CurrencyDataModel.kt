package com.shady.data.model

import com.shady.domain.entity.Rate

data class CurrencyDataModel(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: List<Rate>?,
    val timestamp: Int?,
)