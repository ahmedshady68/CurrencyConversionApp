package com.shady.data.repo

import com.shady.data.mapper.CurrencyDataMapper
import com.shady.data.remote.ApiService
import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.repo.CurrencyRepo

class CurrencyRepoImpl(private val apiService: ApiService, private val mapper: CurrencyDataMapper) :
    CurrencyRepo {
    override suspend fun getCurrencyFromRemote(): CurrencyDomainModel =
        mapper.apply(apiService.getCurrencyList())
}