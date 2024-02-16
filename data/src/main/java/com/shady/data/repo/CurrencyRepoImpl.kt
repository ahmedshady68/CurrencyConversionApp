package com.shady.data.repo

import androidx.room.withTransaction
import com.shady.data.local.CurrencyDatabase
import com.shady.data.mapper.CurrencyDataMapper
import com.shady.data.remote.ApiService
import com.shady.domain.entity.CurrencyDomainModel
import com.shady.domain.repo.CurrencyRepo

class CurrencyRepoImpl(
    private val apiService: ApiService,
    private val db: CurrencyDatabase,
    private val mapper: CurrencyDataMapper,
) :
    CurrencyRepo {
    override suspend fun getCurrencyFromRemote(): CurrencyDomainModel =
        mapper.apply(apiService.getCurrencyList())

    override suspend fun getCurrencyFromLocal(): CurrencyDomainModel {
        return mapper.apply(db.currencyDao().getCurrency())
    }

    override suspend fun cacheRestaurant(currency: CurrencyDomainModel) {
        db.withTransaction {
            db.currencyDao().deleteAllCurrency()
            db.currencyDao()
                .insert(currency)
        }
    }
}