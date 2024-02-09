package com.shady.domain.usecase

import com.shady.domain.repo.CurrencyRepo

class GetCurrencyUseCase(private val repo: CurrencyRepo) : CurrencyUseCase {
    override suspend operator fun invoke() = repo.getCurrencyFromRemote()
}