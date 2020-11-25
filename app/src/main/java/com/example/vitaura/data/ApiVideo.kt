package com.example.vitaura.data


import com.google.gson.annotations.SerializedName

data class ApiVideo(
    @SerializedName("data")
    val data: List<Data?>?,
) {
    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("links")
        val links: Links?,
        @SerializedName("relationships")
        val relationships: Relationships?,
        @SerializedName("type")
        val type: String?
    ) {
        data class Attributes(
            @SerializedName("body")
            val body: Any?,
            @SerializedName("changed")
            val changed: String?,
            @SerializedName("created")
            val created: String?,
            @SerializedName("default_langcode")
            val defaultLangcode: Boolean?,
            @SerializedName("drupal_internal__nid")
            val drupalInternalNid: Int?,
            @SerializedName("drupal_internal__vid")
            val drupalInternalVid: Int?,
            @SerializedName("field_title2")
            val fieldTitle2: Any?,
            @SerializedName("field_youtube")
            val fieldYoutube: FieldYoutube?,
            @SerializedName("langcode")
            val langcode: String?,
            @SerializedName("metatag")
            val metatag: Any?,
            @SerializedName("path")
            val path: Path?,
            @SerializedName("promote")
            val promote: Boolean?,
            @SerializedName("revision_log")
            val revisionLog: Any?,
            @SerializedName("revision_timestamp")
            val revisionTimestamp: String?,
            @SerializedName("revision_translation_affected")
            val revisionTranslationAffected: Boolean?,
            @SerializedName("status")
            val status: Boolean?,
            @SerializedName("sticky")
            val sticky: Boolean?,
            @SerializedName("title")
            val title: String?
        ) {
            data class FieldYoutube(
                @SerializedName("input")
                val input: String?,
                @SerializedName("video_id")
                val videoId: String?
            )

            data class Path(
                @SerializedName("alias")
                val alias: String?,
                @SerializedName("langcode")
                val langcode: String?,
                @SerializedName("pid")
                val pid: Int?
            )
        }

        data class Links(
            @SerializedName("self")
            val self: Self?
        ) {
            data class Self(
                @SerializedName("href")
                val href: String?
            )
        }

        data class Relationships(
            @SerializedName("field_image")
            val fieldImage: FieldImage?,
        ) {
            data class FieldImage(
                @SerializedName("data")
                val `data`: Data?,
                @SerializedName("links")
                val links: Links?
            ) {
                data class Data(
                    @SerializedName("id")
                    val id: String?,
                    @SerializedName("meta")
                    val meta: Meta?,
                    @SerializedName("type")
                    val type: String?
                ) {
                    data class Meta(
                        @SerializedName("alt")
                        val alt: String?,
                        @SerializedName("height")
                        val height: Int?,
                        @SerializedName("imageDerivatives")
                        val imageDerivatives: ImageDerivatives?,
                        @SerializedName("title")
                        val title: String?,
                        @SerializedName("width")
                        val width: Int?
                    ) {
                        data class ImageDerivatives(
                            @SerializedName("links")
                            val links: Links?
                        ) {
                            data class Links(
                                @SerializedName("actions_slider")
                                val actionsSlider: ActionsSlider?,
                                @SerializedName("main_slider")
                                val mainSlider: MainSlider?,
                                @SerializedName("photo_column_2")
                                val photoColumn2: PhotoColumn2?,
                                @SerializedName("photo_column_3")
                                val photoColumn3: PhotoColumn3?,
                                @SerializedName("pop_therapy_slider")
                                val popTherapySlider: PopTherapySlider?,
                                @SerializedName("video_preview")
                                val videoPreview: VideoPreview?
                            ) {
                                data class ActionsSlider(
                                    @SerializedName("href")
                                    val href: String?,
                                    @SerializedName("meta")
                                    val meta: Meta?
                                ) {
                                    data class Meta(
                                        @SerializedName("rel")
                                        val rel: List<String?>?
                                    )
                                }

                                data class MainSlider(
                                    @SerializedName("href")
                                    val href: String?,
                                    @SerializedName("meta")
                                    val meta: Meta?
                                ) {
                                    data class Meta(
                                        @SerializedName("rel")
                                        val rel: List<String?>?
                                    )
                                }

                                data class PhotoColumn2(
                                    @SerializedName("href")
                                    val href: String?,
                                    @SerializedName("meta")
                                    val meta: Meta?
                                ) {
                                    data class Meta(
                                        @SerializedName("rel")
                                        val rel: List<String?>?
                                    )
                                }

                                data class PhotoColumn3(
                                    @SerializedName("href")
                                    val href: String?,
                                    @SerializedName("meta")
                                    val meta: Meta?
                                ) {
                                    data class Meta(
                                        @SerializedName("rel")
                                        val rel: List<String?>?
                                    )
                                }

                                data class PopTherapySlider(
                                    @SerializedName("href")
                                    val href: String?,
                                    @SerializedName("meta")
                                    val meta: Meta?
                                ) {
                                    data class Meta(
                                        @SerializedName("rel")
                                        val rel: List<String?>?
                                    )
                                }

                                data class VideoPreview(
                                    @SerializedName("href")
                                    val href: String?,
                                    @SerializedName("meta")
                                    val meta: Meta?
                                ) {
                                    data class Meta(
                                        @SerializedName("rel")
                                        val rel: List<String?>?
                                    )
                                }
                            }
                        }
                    }
                }

                data class Links(
                    @SerializedName("related")
                    val related: Related?,
                    @SerializedName("self")
                    val self: Self?
                ) {
                    data class Related(
                        @SerializedName("href")
                        val href: String?
                    )

                    data class Self(
                        @SerializedName("href")
                        val href: String?
                    )
                }
            }
        }
    }
}