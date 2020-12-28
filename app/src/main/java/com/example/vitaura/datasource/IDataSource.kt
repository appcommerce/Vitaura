package com.example.vitaura.datasource

import com.example.vitaura.data.*
import com.google.gson.JsonObject
import io.reactivex.Observable

interface IDataSource {
    fun getSlides(): Observable<List<ApiSlider>>
    fun getServiceActions(): Observable<List<ApiServiceAction>>
    fun getServices(): Observable<List<ApiService>>
    fun getPages(): Observable<ApiPages>
    fun getNodeDoctors(): Observable<ApiDoctors>
    fun getGallery(): Observable<ApiGalleries>
    fun getChangeGallery(): Observable<List<ApiChangeFile>>
    fun getDoctor(id: String):Observable<ApiCurrentDoctor>
    fun getFeedback(): Observable<ApiPatients>
    fun getPopularProblems(): Observable<List<ApiPopularProblems>>
    fun getTaxonomyService(id: String): Observable<ApiTaxonomyService>
    fun getServiceNodeDoctors(id: String): Observable<ApiDoctors>
    fun getPrices(): Observable<JsonObject>
    fun getVideoAlbums(): Observable<ApiVideoAlbums>
    fun getVideo(id: String): Observable<ApiVideo>
    fun getAllActions(): Observable<ApiActions>
    fun getActionById(id: String): Observable<ApiAction>
    fun getPage(id: String): Observable<ApiPage>
}