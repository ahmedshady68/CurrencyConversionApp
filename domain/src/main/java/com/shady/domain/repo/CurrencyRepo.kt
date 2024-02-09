package com.shady.domain.repo

import com.shady.domain.entity.CurrencyDomainModel

interface CurrencyRepo {
    suspend fun getCurrencyFromRemote(): CurrencyDomainModel?
}