package com.shady.currencyconversionapp.di

import android.app.Application
import androidx.room.Room
import com.shady.data.local.CurrencyDatabase
import com.shady.data.mapper.CurrencyDataMapper
import com.shady.data.remote.ApiService
import com.shady.data.repo.CurrencyRepoImpl
import com.shady.domain.repo.CurrencyRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RepoModule {
    @Provides
    fun provideRepo(apiService: ApiService,currencyDatabase: CurrencyDatabase,  mapper: CurrencyDataMapper): CurrencyRepo {
        return CurrencyRepoImpl(apiService, currencyDatabase, mapper)
    }
    @Provides
    @Singleton
    fun provideDatabase(app: Application) : CurrencyDatabase =
        Room.databaseBuilder(app, CurrencyDatabase::class.java, "currency_database")
            .build()
}