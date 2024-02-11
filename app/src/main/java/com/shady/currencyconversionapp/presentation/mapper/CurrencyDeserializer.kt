package com.shady.currencyconversionapp.presentation.mapper

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.shady.data.model.CurrencyDataModel
import com.shady.domain.entity.Rate
import java.lang.reflect.Type

class CurrencyDeserializer : JsonDeserializer<CurrencyDataModel> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): CurrencyDataModel {
        if (json == null || context == null) {
            // handle error here
            throw Exception("Error")
        }
        val obj = json.asJsonObject

        // let Gson handle the other 3 properties
        val base = context.deserialize<String?>(obj.get("base"), String::class.java)
        val disclaimer = context.deserialize<String?>(obj.get("disclaimer"), String::class.java)
        val license = context.deserialize<String?>(obj.get("license"), String::class.java)
        val date = context.deserialize<Int?>(obj.get("timestamp"), Int::class.java)

        // create List<Rate> from the rates JsonObject
        val ratesSet = obj.get("rates").asJsonObject.entrySet()
        val ratesList = ratesSet.map {
            val code = it.key
            val value = it.value.asFloat
            Rate(code, value)
        }

        return CurrencyDataModel(
            base = base,
            disclaimer = disclaimer,
            license = license,
            rates = ratesList,
            timestamp = date
        )
    }
}