package com.shady.data.model

import com.shady.domain.entity.CurrencyDomainRates

data class CurrencyDataModel(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: CurrencyDomainRates?,
    val timestamp: Int?,
)