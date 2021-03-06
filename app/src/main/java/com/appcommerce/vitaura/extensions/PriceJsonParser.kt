package com.appcommerce.vitaura.extensions

import com.appcommerce.vitaura.pojo.Prices
import com.appcommerce.vitaura.pojo.PricesCascade
import com.google.gson.*

object PriceJsonParser{
    fun createCascadePrice(json: JsonObject): Pair<MutableList<Prices>, MutableList<PricesCascade>>{
        val cascadeObject = JsonObject()
        val cascadeArray = JsonArray()
        val resultSimple = mutableListOf<Prices>()
        val resultCascade = mutableListOf<PricesCascade>()
        //TODO: Нужно пофиксить формирование cascadeObject
        for ((_, value) in json.asJsonObject.entrySet()){
            if (value.isJsonObject){
                for ((key, _) in json.get("data").asJsonObject.entrySet()){
                    val v = value.asJsonObject.getAsJsonObject(key)

                    if (v.get("data").isJsonArray){
                        resultSimple.add(Gson().fromJson(v, Prices::class.java))
                    }

                    if (v.get("data").isJsonObject){
                        cascadeObject.addProperty("name", v.get("name").asString)
                        for ((x, _) in v.get("data").asJsonObject.entrySet()){
                            cascadeArray.add(v.get("data").asJsonObject.getAsJsonObject(x))
                        }
                        cascadeObject.add("data", cascadeArray)
                        resultCascade.add(Gson().fromJson(cascadeObject, PricesCascade::class.java))
                    }
                }
            }
        }
        return Pair(resultSimple, resultCascade)
    }
}