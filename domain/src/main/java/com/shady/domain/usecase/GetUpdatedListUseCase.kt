package com.shady.domain.usecase

import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.entity.Rate

class GetUpdatedListUseCase(
    private val getCurrencyUseCase: CurrencyUseCase,
) : UpdatedListUseCase {
    override suspend operator fun invoke(
        newRate: Float,
        newText: String,
    ): CurrencyDomainModel {
        var amountInUSD = 1f
        if (newText.isNotEmpty()) {
            amountInUSD = (newText.toFloat() / newRate)
        }
        val oldList: CurrencyDomainModel? = getCurrencyUseCase()
        return CurrencyDomainModel(
            oldList?.base,
            oldList?.disclaimer,
            oldList?.license,
            oldList?.rates?.map {
                Rate(
                    code = it.code,
                    value = amountInUSD * it.value
                )
            },
            oldList?.timestamp
        )
    }
}