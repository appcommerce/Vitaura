package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiPages(@Expose @SerializedName("data")val pages: List<ApiPage>?){
    data class ApiPage(@Expose @SerializedName("attributes")val data: Data?)
    data class Data(@Expose @SerializedName("title")val title: String?,
                    @Expose @SerializedName("body")val body: Body?)
    data class Body(@Expose @SerializedName("processed")val text: String?)
}