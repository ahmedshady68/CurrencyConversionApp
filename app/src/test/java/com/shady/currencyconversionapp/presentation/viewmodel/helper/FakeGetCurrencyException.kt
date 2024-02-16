package com.shady.currencyconversionapp.presentation.viewmodel.helper

import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.usecase.CurrencyUseCase

open class FakeGetCurrencyException : CurrencyUseCase {
    private var exception: Exception = Exception()

    override suspend fun invoke(newRate: Float?, newText: String?): CurrencyDomainModel? =
        throw exception

    fun setException(e: Exception) {
        exception = e
    }
}