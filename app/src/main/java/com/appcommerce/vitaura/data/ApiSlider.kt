package com.appcommerce.vitaura.data


import com.google.gson.annotations.SerializedName

data class ApiSlider(
    @SerializedName("body")
    val body: String?,
    @SerializedName("field_photo")
    val fieldPhoto: String?,
    @SerializedName("field_photo2")
    val fieldPhoto2: String?,
    @SerializedName("field_photo3")
    val fieldPhoto3: String?,
    @SerializedName("field_photos_mobile")
    val fieldPhotosMobile: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("view_node")
    val viewNode: String?
)