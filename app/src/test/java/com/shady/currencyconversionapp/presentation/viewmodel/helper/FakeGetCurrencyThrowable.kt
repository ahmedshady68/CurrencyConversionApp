package com.shady.currencyconversionapp.presentation.viewmodel.helper

import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.usecase.CurrencyUseCase

open class FakeGetCurrencyThrowable : CurrencyUseCase {
    private var throwable: Throwable = Throwable()

    override suspend fun invoke(newRate: Float?, newText: String?): CurrencyDomainModel? =
        throw throwable

    fun setThrowable(throwable: Throwable) {
        this.throwable = throwable
    }
}