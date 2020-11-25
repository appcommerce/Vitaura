package com.example.vitaura.datasource

import com.example.vitaura.data.ApiAction
import com.example.vitaura.data.ApiService
import com.example.vitaura.data.ApiSlider
import io.reactivex.Observable

interface IDataSource {
    fun getSlides(): Observable<List<ApiSlider>>
    fun getActions(): Observable<List<ApiAction>>
    fun getServices(): Observable<List<ApiService>>
    fun getServiceById(id: Int): Observable<ApiService>
}