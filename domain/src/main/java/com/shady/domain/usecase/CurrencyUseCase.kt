package com.shady.domain.usecase

import com.shady.domain.entity.CurrencyDomainModel

interface CurrencyUseCase {
    suspend operator fun invoke(): CurrencyDomainModel?
}