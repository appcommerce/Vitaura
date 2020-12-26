package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiVideoAlbums(@Expose @SerializedName("data")val data: List<Data>?){
    data class Data(@Expose @SerializedName("id")val id: String?,
            @Expose @SerializedName("attributes")val attributes: Attributes?)
    data class Attributes(@Expose @SerializedName("title")val title: String?,
                          @Expose @SerializedName("field_youtube")val youtube: Youtube?)
    data class Youtube(@Expose @SerializedName("input")val url: String?,
                       @Expose @SerializedName("id")val id: String?)
}
