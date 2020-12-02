package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiCurrentDoctor(@Expose @SerializedName("description")val description: String?,
                            @Expose @SerializedName("mini_description")val miniDescription: String?,
                            @Expose @SerializedName("name")val name: String?,
                            @Expose @SerializedName("photo_name")val photoName: String?,
                            @Expose @SerializedName("spec")val spec: String?)
