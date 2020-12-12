package com.example.vitaura.datasource.remote

import com.example.vitaura.data.*
import com.example.vitaura.datasource.IDataSource
import io.reactivex.Observable

class RemoteDataSource(private val restDataSource: RetrofitProvider): IDataSource{
    override fun getSlides(): Observable<List<ApiSlider>> = restDataSource.getService().getSlides()
    override fun getActions(): Observable<List<ApiServiceAction>> = restDataSource.getService().getServiceActions()
    override fun getServices(): Observable<List<ApiService>> = restDataSource.getService().getServices()
    override fun getServiceById(id: Int): Observable<ApiService> = restDataSource.getService().getServiceById(id)
    override fun getPages(): Observable<ApiPages> = restDataSource.getService().getPages()
    override fun getNodeDoctors(): Observable<ApiDoctors> = restDataSource.getService().getNodeDoctors()
    override fun getGallery(): Observable<ApiGalleries> = restDataSource.getService().getGallery()
    override fun getChangeGallery(): Observable<List<ApiChangeFile>> = restDataSource.getService().getChangeGallery()
    override fun getDoctors(): Observable<List<ApiCurrentDoctor>> = restDataSource.getService().getDoctors()
    override fun getFeedback(): Observable<ApiPatients> = restDataSource.getService().getFeedback()
}