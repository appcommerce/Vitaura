package com.example.vitaura.datasource.remote

import com.example.vitaura.data.*
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRestRequests {
    @GET("/rest/slides/front")
    fun getSlides(): Observable<List<ApiSlider>>
    @GET("/rest/actions/service/all")
    fun getServiceActions(): Observable<List<ApiServiceAction>>
    @GET("/rest_mobile/services")
    fun getServices():Observable<List<ApiService>>
    @GET("/jsonapi/node/page")
    fun getPages(): Observable<ApiPages>
    @GET("/jsonapi/node/doctors?include=field_photo&filter[status][value]=1")
    fun getNodeDoctors(): Observable<ApiDoctors>
    @GET("/jsonapi/node/doctors/{id}?include=field_photo")
    fun getDoctor(@Path("id")id: String):Observable<ApiCurrentDoctor>
    @GET("/jsonapi/node/gallery/a4e3691a-17ba-4b6b-8653-439382d67f4a?include=field_gallery_image")
    fun getGallery(): Observable<ApiGalleries>
    @GET("/rest/do-i-posle")
    fun getChangeGallery(): Observable<List<ApiChangeFile>>
    @GET("/jsonapi/node/patients")
    fun getFeedback(): Observable<ApiPatients>
    @GET("/rest/problems/popular_front")
    fun getPopularProblems(): Observable<List<ApiPopularProblems>>
    @GET("/jsonapi/taxonomy_term/services")
    fun getTaxonomyServiceById(@Query("filter[drupal_internal__tid]") id: String): Observable<ApiTaxonomyService>
    @GET("/jsonapi/node/doctors?include=field_photo")
    fun getServiceNodeDoctors(@Query("filter[field_services.id]")id: String): Observable<ApiDoctors>
    @GET("/blocks_for_page/special_links/price.html")
    fun getPrices(): Observable<JsonObject>
    @GET("/jsonapi/node/video?filter[status][value]=1")
    fun getVideoAlbums(): Observable<ApiVideoAlbums>
    @GET("/jsonapi/node/video/{id}")
    fun getVideo(@Path("id") id: String): Observable<ApiVideo>
    @GET("/jsonapi/node/actions?filter[status][value]=1&filter[datefilter][condition][path]=field_action_date&filter[datefilter][condition][operator]=>%3D&filter[datefilter][condition][value]=2020-12-27&include=field_image&fields[node--actions]=title%2Cpath%2Cbody&fields[file--file]=derivatives%2Calt%2Ctitle&sort=-created")
    fun getActions(): Observable<ApiActions>
    @GET("/jsonapi/node/actions/{id}?filter[status][value]=1&filter[datefilter][condition][path]=field_action_date&filter[datefilter][condition][operator]=>%3D&filter[datefilter][condition][value]=2020-12-27&include=field_image&fields[node--actions]=title%2Cpath%2Cbody&fields[file--file]=derivatives%2Calt%2Ctitle&sort=-created")
    fun getActionById(@Path("id")id: String): Observable<ApiAction>
}