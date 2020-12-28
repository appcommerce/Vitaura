package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiPage(@Expose @SerializedName("data")val data: Data?,
                   @Expose @SerializedName("included")val photos: List<Included>?){
    data class Data(@Expose @SerializedName("attributes")val attributes: Attributes?,
                    @Expose @SerializedName("relationships")val relationships: Relationships?)
    data class Relationships(@Expose @SerializedName("field_files")val files: Files?)
    data class Files(@Expose @SerializedName("data")val data: List<DataFiles>?)
    data class DataFiles(@Expose @SerializedName("meta")val meta: Meta?)
    data class Meta(@Expose @SerializedName("description")val description: String?)
    data class Attributes(@Expose @SerializedName("title")val title: String?,
                          @Expose @SerializedName("body")val body: Body?)
    data class Body(@Expose @SerializedName("value")val description: String?)
    data class Included(@Expose @SerializedName("links")val links: Links?)
    data class Links(@Expose @SerializedName("main_slider")val image: Image?)
    data class Image(@Expose @SerializedName("href")val url: String?)
}
