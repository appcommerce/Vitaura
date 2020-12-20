package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//@JsonAdapter(PriceDeserializer::class)
data class ApiPrice(@Expose @SerializedName("data")val data: Data?) {
    data class Data(@Expose val members: List<Members>?){
        data class Members(@Expose val name: String?,
                           @Expose @SerializedName("data")val data: MutableList<Price>?){
            data class Price(@Expose @SerializedName("field_price_item")val item: String?,
                             @Expose @SerializedName("field_price_sum")val sum: String?)
        }
    }
}