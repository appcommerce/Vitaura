package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiGalleries(@Expose @SerializedName("included")val include: List<Include>?){
    data class Include(@Expose @SerializedName("links")val link: Link?)
    data class Link(@Expose @SerializedName("photo_column_2")val photo: Photo?)
    data class Photo(@Expose @SerializedName("href")val url: String?)
}
