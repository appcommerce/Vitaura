package com.example.vitaura.pojo

data class PricesCascade(val name: String?,
                        val data: MutableList<Data>?){
    data class Data(val name: String?,
            val data: List<Price>?)
    data class Price(val field_price_item: String?, val field_price_sum: String?)
}