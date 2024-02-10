package com.shady.currencyconversionapp.di

import com.shady.currencyconversionapp.presentation.mapper.CurrencyAppMapper
import com.shady.data.mapper.CurrencyDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    fun provideDateMapper(): CurrencyDataMapper {
        return CurrencyDataMapper()
    }

    @Provides
    fun providePresentationMapper(): CurrencyAppMapper {
        return CurrencyAppMapper()
    }
}