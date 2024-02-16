package com.shady.domain.usecase

import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.entity.Rate
import com.shady.domain.repo.CurrencyRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCurrencyUseCaseTest {
    private val repo: CurrencyRepo = mockk()
    private val useCase: CurrencyUseCase = GetCurrencyUseCase(repo)

    @Test
    fun `call repo When call usa case with initial state`() = runTest {
        // Given
        coEvery { repo.getCurrencyFromRemote() } returns mockk()
        // When
        useCase.invoke()
        // Then
        coVerify { repo.getCurrencyFromRemote() }
    }

    @Test
    fun `call repo When call usa case with a specific rate and amount`() = runTest {
        // Given
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
        val model = CurrencyDomainModel(
            base = "",
            disclaimer = "",
            license = "",
            rates = rateModel,
            timestamp = 5
        )
        coEvery { repo.getCurrencyFromRemote() } returns model
        val rate = 5f
        val amount = "50"
        // When
        useCase.invoke(rate, amount)
        // Then
        coVerify { repo.getCurrencyFromRemote() }
    }
}