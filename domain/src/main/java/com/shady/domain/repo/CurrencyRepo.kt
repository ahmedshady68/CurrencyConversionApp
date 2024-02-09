package com.shady.domain.repo

import com.shady.domain.entity.CurrencyResponse

interface CurrencyRepo {
    suspend fun getCurrencyFromRemote(): CurrencyResponse?
}