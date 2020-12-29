package com.appcommerce.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiVideo(@Expose @SerializedName("data")val data: Data?){
    data class Data(@Expose @SerializedName("id")val id: String?,
                    @Expose @SerializedName("attributes")val attributes: Attributes?)
    data class Attributes(@Expose @SerializedName("title")val title: String?,
                          @Expose @SerializedName("field_youtube")val youtube: Youtube?)
    data class Youtube(@Expose @SerializedName("input")val url: String?,
                       @Expose @SerializedName("video_id")val id: String?)
}
