package com.shady.domain.usecase

import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.entity.Rate
import com.shady.domain.repo.CurrencyRepo

class GetCurrencyUseCase(private val repo: CurrencyRepo) : CurrencyUseCase {
    override suspend operator fun invoke(newRate: Float?, newText: String?): CurrencyDomainModel? {
        if (newRate != null && newText != null) {
            var amountInUSD = 1f
            if (newText.isNotEmpty()) {
                amountInUSD = (newText.toFloat() / newRate)
            }
            val oldList: CurrencyDomainModel? = repo.getCurrencyFromRemote()
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
        } else {
            return repo.getCurrencyFromRemote()
        }
    }
}
