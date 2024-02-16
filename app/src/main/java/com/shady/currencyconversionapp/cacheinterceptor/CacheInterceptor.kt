package com.shady.currencyconversionapp.cacheinterceptor

import com.shady.currencyconversionapp.utils.CurrencyConstants.CACHE_CONTROL
import com.shady.currencyconversionapp.utils.CurrencyConstants.MAX_AGE
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(MAX_AGE, TimeUnit.MINUTES)
            .build()
        return response.newBuilder()
            .header(CACHE_CONTROL, cacheControl.toString())
            .build()
    }
}