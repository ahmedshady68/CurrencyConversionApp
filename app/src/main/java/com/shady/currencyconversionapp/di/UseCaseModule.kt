package com.shady.currencyconversionapp.di

import com.shady.domain.repo.CurrencyRepo
import com.shady.domain.usecase.CurrencyUseCase
import com.shady.domain.usecase.GetCurrencyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(repo: CurrencyRepo): CurrencyUseCase {
        return GetCurrencyUseCase(repo)
    }
}