package com.example.vitaura.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiGalleries(@Expose @SerializedName("data")val galleries: List<ApiGallery>?){
    data class ApiGallery(@Expose @SerializedName("attributes")val attributes: GalleryAttributes?,
                          @Expose @SerializedName("relationships")val relationship: GalleryRelationship?)
    data class GalleryAttributes(@Expose @SerializedName("title")val title: String?)
    data class GalleryRelationship(@Expose @SerializedName("field_gallery_image")val galleryImageField: GalleryImageField?)
    data class GalleryImageField(@Expose @SerializedName("data")val files: List<GalleryFile>?)
    data class GalleryFile(@Expose @SerializedName("attributes")val attributes: GalleryFileAttributes?,
                           @Expose @SerializedName("id")val id: String?)
    data class GalleryFileAttributes(@Expose @SerializedName("uri")val uri: GalleryFileUri?)
    data class GalleryFileUri(@Expose @SerializedName("url")val url: String?)
}
