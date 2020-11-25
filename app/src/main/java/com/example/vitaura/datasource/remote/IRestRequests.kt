package com.example.vitaura.datasource.remote

import com.example.vitaura.data.ApiAction
import com.example.vitaura.data.ApiService
import com.example.vitaura.data.ApiSlider
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface IRestRequests {
    @GET("/rest/slides/front")
    fun getSlides(): Observable<List<ApiSlider>>
    @GET("/rest/actions/service/all")
    fun getActions(): Observable<List<ApiAction>>
    @GET("/rest_mobile/services")
    fun getServices():Observable<List<ApiService>>
    @GET("/rest_mobile/services/{id}")
    fun getServiceById(@Path("id")id: Int): Observable<ApiService>
}