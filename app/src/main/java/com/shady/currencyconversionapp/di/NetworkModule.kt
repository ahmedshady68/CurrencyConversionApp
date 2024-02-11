package com.shady.currencyconversionapp.di

import com.google.gson.GsonBuilder
import com.shady.currencyconversionapp.presentation.mapper.CurrencyDeserializer
import com.shady.data.model.CurrencyDataModel
import com.shady.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHTTPClient(): OkHttpClient {
        return OkHttpClient().newBuilder().callTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openexchangerates.org/api/")
            .client(okHttpClient).addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapter(CurrencyDataModel::class.java, CurrencyDeserializer())
                        .create()
                )
            ).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}