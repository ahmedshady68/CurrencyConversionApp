package com.shady.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shady.domain.entity.Rate
@Entity("currency_table")
data class CurrencyEntity(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: List<Rate>?,
    @PrimaryKey val timestamp: Int?,
)
