package com.appcommerce.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiChangeFile(@Expose @SerializedName("field_gallery_image")val url: String?,
                         @Expose @SerializedName("title")val title: String?)
