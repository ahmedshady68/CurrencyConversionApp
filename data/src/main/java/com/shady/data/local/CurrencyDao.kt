package com.shady.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shady.data.model.CurrencyDataModel
import com.shady.domain.entity.CurrencyDomainModel

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: CurrencyDomainModel)

    @Query("select * from currency_table")
    fun getCurrency(): CurrencyDataModel

    @Query("DELETE FROM currency_table")
    suspend fun deleteAllCurrency()
}