package com.shady.domain.usecase

import com.shady.domain.entity.CurrencyDomainModel

interface UpdatedListUseCase {
    suspend operator fun invoke(
        newRate: Float,
        newText: String
    ): CurrencyDomainModel?
}