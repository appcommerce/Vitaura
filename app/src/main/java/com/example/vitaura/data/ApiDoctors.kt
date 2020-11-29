package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiDoctors(@Expose @SerializedName("data")val data: List<Doctors>?){
    data class Doctors(@Expose @SerializedName("attributes")val attributes: DoctorAttributes?,
                       @Expose @SerializedName("relationships")val relations: DoctorRelationships?)
    data class DoctorAttributes(@Expose @SerializedName("body")val body: DoctorsTextField?,
                                @Expose @SerializedName("field_mobile_price_spec") val price: String?,
                                @Expose @SerializedName("field_mobile_education")val education: DoctorsTextField?,
                                @Expose @SerializedName("field_mobile_information")val information: DoctorsTextField?,
                                @Expose @SerializedName("field_post")val post: String?,
                                @Expose @SerializedName("field_mobile_short_description")val shortDescription: DoctorsTextField?,
                                @Expose @SerializedName("field_mobile_specialization")val specialization: DoctorsTextField?,
                                @Expose @SerializedName("title")val title: String?,
                                @Expose @SerializedName("field_weight")val weight: Int?)
    data class DoctorRelationships(@Expose @SerializedName("field_services")val services: DoctorServiceData?)
    data class DoctorServiceData(@Expose @SerializedName("data")val service: List<DoctorService>?)
    data class DoctorService(@Expose @SerializedName("id")val id: Int?)
    data class DoctorsTextField(@Expose @SerializedName("value")val text: String?)
}
