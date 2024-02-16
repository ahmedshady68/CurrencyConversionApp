package com.shady.domain.repo

import com.shady.domain.entity.CurrencyDomainModel

interface CurrencyRepo {
    suspend fun getCurrencyFromRemote(): CurrencyDomainModel?
    suspend fun getCurrencyFromLocal(): CurrencyDomainModel?
    suspend fun cacheRestaurant(currency: CurrencyDomainModel)
}