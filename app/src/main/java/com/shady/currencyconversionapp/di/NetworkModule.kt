package com.shady.currencyconversionapp.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.shady.currencyconversionapp.cacheinterceptor.CacheInterceptor
import com.shady.currencyconversionapp.presentation.mapper.CurrencyDeserializer
import com.shady.currencyconversionapp.utils.CurrencyConstants.BASE_URL
import com.shady.currencyconversionapp.utils.CurrencyConstants.HEADER_NAME
import com.shady.currencyconversionapp.utils.CurrencyConstants.MAX_SIZE
import com.shady.currencyconversionapp.utils.CurrencyConstants.TIME_OUT
import com.shady.data.model.CurrencyDataModel
import com.shady.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHTTPClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .cache(Cache(File(appContext.cacheDir, HEADER_NAME), MAX_SIZE)) // 10 MiB
            .addNetworkInterceptor(CacheInterceptor())
            .readTimeout(TIME_OUT, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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