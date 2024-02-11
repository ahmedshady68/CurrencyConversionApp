package com.shady.domain.entity

data class CurrencyDomainModel(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: List<Rate>?,
    val timestamp: Int?,
)