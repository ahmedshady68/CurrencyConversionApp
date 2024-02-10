package com.shady.data.remote

import com.shady.data.BuildConfig
import com.shady.data.model.CurrencyDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("latest.json?")
    suspend fun getCurrencyList(@Query("app_id") key: String = BuildConfig.API_KEY): CurrencyDataModel?
}