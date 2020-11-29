package com.example.vitaura.data


import com.google.gson.annotations.SerializedName

data class ApiServiceAction(
    @SerializedName("body")
    val body: String?,
    @SerializedName("field_image")
    val fieldImage: String?,
    @SerializedName("field_image2")
    val fieldImage2: String?,
    @SerializedName("field_image_preview")
    val fieldImagePreview: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("view_node")
    val viewNode: String?
)