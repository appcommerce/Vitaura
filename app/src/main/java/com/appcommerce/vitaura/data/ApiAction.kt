package com.appcommerce.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiAction(@Expose @SerializedName("data")val data: Data?,
                     @Expose @SerializedName("included")val photos: List<Included>?){
    data class Included(@Expose @SerializedName("links")val links: Links?)
    data class Links(@Expose @SerializedName("photo_column_2")val imageMin: Image?,
                     @Expose @SerializedName("main_slider")val imageMax: Image?)
    data class Image(@Expose @SerializedName("href")val url: String?)
    data class Data(@Expose @SerializedName("id")val id: String?,
                    @Expose @SerializedName("attributes")val attributes: Attributes?)
    data class Attributes(@Expose @SerializedName("title")val title: String?,
                          @Expose @SerializedName("body")val body: Body?)
    data class Body(@Expose @SerializedName("value")val value: String?)
}
