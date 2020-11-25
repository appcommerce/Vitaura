package com.example.vitaura.datasource.remote

import com.example.vitaura.data.ApiAction
import com.example.vitaura.data.ApiService
import com.example.vitaura.data.ApiSlider
import com.example.vitaura.datasource.IDataSource
import io.reactivex.Observable

class RemoteDataSource(private val restDataSource: RetrofitProvider): IDataSource{
    override fun getSlides(): Observable<List<ApiSlider>> = restDataSource.getService().getSlides()
    override fun getActions(): Observable<List<ApiAction>> = restDataSource.getService().getActions()
    override fun getServices(): Observable<List<ApiService>> = restDataSource.getService().getServices()
    override fun getServiceById(id: Int): Observable<ApiService> = restDataSource.getService().getServiceById(id)
}