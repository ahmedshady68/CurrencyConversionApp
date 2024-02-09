package com.shady.domain.usecase

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
    fun `call repo When call usa case`() = runTest {
        // Given
        coEvery { repo.getCurrencyFromRemote() } returns mockk()
        // When
        useCase.invoke()
        // Then
        coVerify { repo.getCurrencyFromRemote() }
    }
}