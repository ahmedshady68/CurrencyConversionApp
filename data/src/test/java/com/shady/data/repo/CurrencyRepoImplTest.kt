package com.shady.data.repo

import com.shady.data.mapper.CurrencyDataMapper
import com.shady.data.remote.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CurrencyRepoImplTest {
    private val apiService: ApiService = mockk()
    private val mapper: CurrencyDataMapper = mockk()
    private val repo: CurrencyRepoImpl = CurrencyRepoImpl(apiService, mapper)

    @Test
    fun `call apiService WHEN call the RepoImpl`() = runTest {
        // Given
        coEvery { apiService.getCurrencyList(any()) } returns mockk()
        coEvery { mapper.apply(any()) } returns mockk()
        // When
        repo.getCurrencyFromRemote()
        // Then
        coVerify { apiService.getCurrencyList(any()) }
    }
}