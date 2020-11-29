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
}