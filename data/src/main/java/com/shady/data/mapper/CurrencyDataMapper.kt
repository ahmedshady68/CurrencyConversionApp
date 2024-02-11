package com.shady.data.mapper

import com.shady.data.model.CurrencyDataModel
import com.shady.domain.entity.CurrencyDomainModel

class CurrencyDataMapper {
    fun apply(dataModel: CurrencyDataModel?): CurrencyDomainModel {
        return CurrencyDomainModel(
            base = dataModel?.base ?: "N/A",
            disclaimer = dataModel?.disclaimer ?: "N/A",
            license = dataModel?.license ?: "N/A",
            rates = dataModel?.rates,
            timestamp = dataModel?.timestamp ?: 0
        )
    }
}