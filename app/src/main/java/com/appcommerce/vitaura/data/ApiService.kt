package com.appcommerce.vitaura.data


import com.google.gson.annotations.SerializedName

data class ApiService(
    @SerializedName("field_mobile_description1")
    val fieldMobileDescription1: String?,
    @SerializedName("field_mobile_description2")
    val fieldMobileDescription2: String?,
    @SerializedName("field_mobile_description3")
    val fieldMobileDescription3: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("parent_target_id")
    val parentTargetId: String?,
    @SerializedName("tid")
    val tid: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("type_weight")
    val typeWeight: String?,
    @SerializedName("weight")
    val weight: String?
)