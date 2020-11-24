package com.example.vitaura.datasource.remote

import com.example.vitaura.data.ApiSlider
import io.reactivex.Observable
import retrofit2.http.GET

interface IRestRequests {
    @GET("/rest/slides/front")
    fun getSlides(): Observable<List<ApiSlider>>
}