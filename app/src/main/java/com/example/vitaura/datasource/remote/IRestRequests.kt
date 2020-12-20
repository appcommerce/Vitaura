package com.example.vitaura.datasource.remote

import com.example.vitaura.data.*
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
    @GET("/jsonapi/node/gallery")
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
}