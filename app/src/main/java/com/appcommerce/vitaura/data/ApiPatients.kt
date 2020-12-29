package com.appcommerce.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiPatients(@Expose @SerializedName("data")val data: List<Patient>?) {
    data class Patient(@Expose @SerializedName("attributes") val attributes: PatientAttributes?)
    data class PatientAttributes(
            @Expose @SerializedName("field_feedback_text") val text: TextField?,
            @Expose @SerializedName("title") val title: String?)
    data class TextField(@Expose @SerializedName("processed") val text: String?)
}