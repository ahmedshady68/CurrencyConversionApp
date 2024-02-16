package com.shady.currencyconversionapp.presentation.viewmodel

import com.shady.currencyconversionapp.presentation.mapper.CurrencyAppMapper
import com.shady.currencyconversionapp.presentation.model.CurrencyAppModel
import com.shady.currencyconversionapp.presentation.model.CurrencyIntent
import com.shady.currencyconversionapp.presentation.viewmodel.helper.FakeGetCurrencyException
import com.shady.currencyconversionapp.presentation.viewmodel.helper.FakeGetCurrencySuccess
import com.shady.currencyconversionapp.presentation.viewmodel.helper.FakeGetCurrencyThrowable
import com.shady.currencyconversionapp.presentation.viewmodel.helper.ViewModelTest
import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.entity.Rate
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyViewModelTest : ViewModelTest() {
    private val mapper: CurrencyAppMapper = CurrencyAppMapper()

    @Test
    fun `get correct response WHEN success in initial call`() = runTest {
        // GIVEN
        // domain model
        val rateModel = listOf(
            Rate(
                code = "AED",
                value = 3.6f
            ),
            Rate(
                code = "USD",
                value = 1f
            ),
        )
        val responseModel = CurrencyDomainModel(
            base = "base",
            disclaimer = "disclaimer",
            license = "license",
            rates = rateModel,
            timestamp = 5
        )
        // presentation model
        val rateModelList = listOf(
            Rate(
                code = "AED",
                value = 3.6f
            ),
            Rate(
                code = "USD",
                value = 1f
            ),
        )
        val stateModel = CurrencyAppModel(
            rates = rateModelList,
            base = "base",
            disclaimer = "disclaimer",
            license = "license",
            timestamp = 5
        )

        val fakeGetMealsSuccess = FakeGetCurrencySuccess()
        val viewModel = CurrencyViewModel(getCurrencyUseCase = fakeGetMealsSuccess, mapper = mapper)
        fakeGetMealsSuccess.emit(responseModel)
        // WHEN
        viewModel.intentChannel.send(CurrencyIntent.GetInitialCurrencyRates)
        // THEN
        assertEquals(stateModel, viewModel.currencyRates.value?.currencyAppModel)
        assertEquals(false, viewModel.currencyRates.value?.isLoading)
        assertEquals(null, viewModel.currencyRates.value?.error)
    }

    @Test
    fun `get correct response WHEN Exception in initial call`() = runTest {
        // GIVEN
        val exception: Exception = Exception()
        val fakeGetMealsException = FakeGetCurrencyException()
        val viewModel =
            CurrencyViewModel(getCurrencyUseCase = fakeGetMealsException, mapper = mapper)
        fakeGetMealsException.setException(exception)
        // WHEN
        viewModel.intentChannel.send(CurrencyIntent.GetInitialCurrencyRates)
        // THEN
        assertEquals(null, viewModel.currencyRates.value?.currencyAppModel)
        assertEquals(false, viewModel.currencyRates.value?.isLoading)
        assertEquals(exception, viewModel.currencyRates.value?.error)
    }

    @Test
    fun `get correct response WHEN Throwable in initial call`() = runTest {
        // GIVEN
        val throwable = Throwable("error")
        val fakeGetMealsThrowable = FakeGetCurrencyThrowable()
        val viewModel =
            CurrencyViewModel(getCurrencyUseCase = fakeGetMealsThrowable, mapper = mapper)
        fakeGetMealsThrowable.setThrowable(throwable)
        // WHEN
        viewModel.intentChannel.send(CurrencyIntent.GetInitialCurrencyRates)
        // THEN
        assertEquals(null, viewModel.currencyRates.value?.currencyAppModel)
        assertEquals(false, viewModel.currencyRates.value?.isLoading)
        assertEquals(throwable, viewModel.currencyRates.value?.error)
    }


    @Test
    fun `get correct response WHEN success in initial call with specific rate and amount`() =
        runTest {
            // GIVEN
            // domain model
            val rateModel = listOf(
                Rate(
                    code = "AED",
                    value = 3.6f
                ),
                Rate(
                    code = "USD",
                    value = 1f
                ),
            )
            val responseModel = CurrencyDomainModel(
                base = "base",
                disclaimer = "disclaimer",
                license = "license",
                rates = rateModel,
                timestamp = 5
            )
            // presentation model
            val rateModelList = listOf(
                Rate(
                    code = "AED",
                    value = 3.6f
                ),
                Rate(
                    code = "USD",
                    value = 1f
                ),
            )
            val stateModel = CurrencyAppModel(
                rates = rateModelList,
                base = "base",
                disclaimer = "disclaimer",
                license = "license",
                timestamp = 5
            )

            val fakeGetMealsSuccess = FakeGetCurrencySuccess()
            val viewModel =
                CurrencyViewModel(getCurrencyUseCase = fakeGetMealsSuccess, mapper = mapper)
            fakeGetMealsSuccess.emit(responseModel)
            // WHEN
            viewModel.intentChannel.send(CurrencyIntent.CalculateCurrencyRate(1f, "10"))
            // THEN
            assertEquals(stateModel, viewModel.currencyRates.value?.currencyAppModel)
            assertEquals(false, viewModel.currencyRates.value?.isLoading)
            assertEquals(null, viewModel.currencyRates.value?.error)
        }

    @Test
    fun `get correct response WHEN Exception in initial call with specific rate and amount`() =
        runTest {
            // GIVEN
            val exception: Exception = Exception()
            val fakeGetMealsException = FakeGetCurrencyException()
            val viewModel =
                CurrencyViewModel(getCurrencyUseCase = fakeGetMealsException, mapper = mapper)
            fakeGetMealsException.setException(exception)
            // WHEN
            viewModel.intentChannel.send(CurrencyIntent.CalculateCurrencyRate(1f, "10"))
            // THEN
            assertEquals(null, viewModel.currencyRates.value?.currencyAppModel)
            assertEquals(false, viewModel.currencyRates.value?.isLoading)
            assertEquals(exception, viewModel.currencyRates.value?.error)
        }

    @Test
    fun `get correct response WHEN Throwable in initial call with specific rate and amount`() =
        runTest {
            // GIVEN
            val throwable = Throwable("error")
            val fakeGetMealsThrowable = FakeGetCurrencyThrowable()
            val viewModel =
                CurrencyViewModel(getCurrencyUseCase = fakeGetMealsThrowable, mapper = mapper)
            fakeGetMealsThrowable.setThrowable(throwable)
            // WHEN
            viewModel.intentChannel.send(CurrencyIntent.CalculateCurrencyRate(1f, "10"))
            // THEN
            assertEquals(null, viewModel.currencyRates.value?.currencyAppModel)
            assertEquals(false, viewModel.currencyRates.value?.isLoading)
            assertEquals(throwable, viewModel.currencyRates.value?.error)
        }
}