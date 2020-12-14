package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiPopularProblems(@Expose @SerializedName("field_photo")val url: String?,
                              @Expose @SerializedName("field_services")val services: String?,
                              @Expose @SerializedName("title")val title: String?)
