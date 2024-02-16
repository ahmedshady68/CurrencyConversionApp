package com.shady.currencyconversionapp.presentation.viewmodel.helper

import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.entity.Rate
import com.shady.domain.usecase.CurrencyUseCase
import kotlinx.coroutines.flow.MutableStateFlow

open class FakeGetCurrencySuccess : CurrencyUseCase {
    // Given
    private val rateModel = listOf(
        Rate(
            code = "AED",
            value = 3.6f
        ),
        Rate(
            code = "USD",
            value = 1f
        ),
    )
    private val modelCurrencyModel: CurrencyDomainModel? = CurrencyDomainModel(
        base = "",
        disclaimer = "",
        license = "",
        rates = rateModel,
        timestamp = 5
    )
    private val stateFlow = MutableStateFlow(modelCurrencyModel)
    open suspend fun emit(responseModel: CurrencyDomainModel?) = stateFlow.emit(responseModel)
    override suspend fun invoke(newRate: Float?, newText: String?): CurrencyDomainModel? =
        stateFlow.value
}
