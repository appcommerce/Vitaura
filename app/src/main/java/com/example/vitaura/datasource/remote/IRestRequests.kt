package com.example.vitaura.datasource.remote

import com.example.vitaura.data.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface IRestRequests {
    @GET("/rest/slides/front")
    fun getSlides(): Observable<List<ApiSlider>>
    @GET("/rest/actions/service/all")
    fun getServiceActions(): Observable<List<ApiServiceAction>>
    @GET("/rest_mobile/services")
    fun getServices():Observable<List<ApiService>>
    @GET("/rest_mobile/services/{id}")
    fun getServiceById(@Path("id")id: Int): Observable<ApiService>
    @GET("/jsonapi/node/page")
    fun getPages(): Observable<ApiPages>
    @GET("/jsonapi/node/doctors")
    fun getNodeDoctors(): Observable<ApiDoctors>
    @GET("/jsonapi/node/gallery")
    fun getGallery(): Observable<ApiGalleries>
}