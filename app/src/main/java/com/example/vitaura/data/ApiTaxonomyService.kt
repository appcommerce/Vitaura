package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiTaxonomyService(@Expose @SerializedName("data")val data: List<Data>?){
    data class Data(@Expose @SerializedName("id")val id: String?,
                    @Expose @SerializedName("attributes")val attributes: AttributesTaxonomy?)
    data class AttributesTaxonomy(@Expose @SerializedName("name")val serviceName: String?,
                                  @Expose @SerializedName("field_description1")val effective: TextField?,
                                  @Expose @SerializedName("field_description3")val benefits: TextField?,
                                  @Expose @SerializedName("field_description4")val contraindications: TextField?)
    data class TextField(@Expose @SerializedName("value")val text: String?)
}