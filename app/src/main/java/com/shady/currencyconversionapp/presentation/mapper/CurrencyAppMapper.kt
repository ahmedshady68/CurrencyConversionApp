package com.shady.currencyconversionapp.presentation.mapper

import com.shady.currencyconversionapp.presentation.model.CurrencyAppModel
import com.shady.domain.entity.CurrencyDomainModel

class CurrencyAppMapper {
    fun apply(dataModel: CurrencyDomainModel?): CurrencyAppModel {
        return CurrencyAppModel(
            base = dataModel?.base ?: "N/A",
            disclaimer = dataModel?.disclaimer ?: "N/A",
            license = dataModel?.license ?: "N/A",
            rates = dataModel?.rates,
            timestamp = dataModel?.timestamp ?: 0
        )
    }
}