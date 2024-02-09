package com.shady.currencyconversionapp.di

import com.shady.data.mapper.CurrencyDataMapper
import com.shady.data.remote.ApiService
import com.shady.data.repo.CurrencyRepoImpl
import com.shady.domain.repo.CurrencyRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object RepoModule {
    @Provides
    fun provideRepo(apiService: ApiService, mapper: CurrencyDataMapper): CurrencyRepo {
        return CurrencyRepoImpl(apiService, mapper)
    }
}