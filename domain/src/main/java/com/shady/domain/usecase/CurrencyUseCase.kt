package com.shady.domain.usecase

import com.shady.domain.entity.CurrencyResponse

interface CurrencyUseCase {
    suspend operator fun invoke(): CurrencyResponse?
}