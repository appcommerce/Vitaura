package com.example.vitaura.datasource

import com.example.vitaura.data.*
import io.reactivex.Observable

interface IDataSource {
    fun getSlides(): Observable<List<ApiSlider>>
    fun getActions(): Observable<List<ApiServiceAction>>
    fun getServices(): Observable<List<ApiService>>
    fun getServiceById(id: Int): Observable<ApiService>
    fun getPages(): Observable<ApiPages>
    fun getNodeDoctors(): Observable<ApiDoctors>
    fun getGallery(): Observable<ApiGalleries>
    fun getChangeGallery(): Observable<List<ApiChangeFile>>
    fun getDoctor(id: String):Observable<ApiCurrentDoctor>
    fun getFeedback(): Observable<ApiPatients>
    fun getPopularProblems(): Observable<List<ApiPopularProblems>>
}