package com.example.vitaura.datasource.remote

import com.example.vitaura.data.*
import com.example.vitaura.datasource.IDataSource
import io.reactivex.Observable

class RemoteDataSource(private val restDataSource: RetrofitProvider): IDataSource{
    override fun getSlides(): Observable<List<ApiSlider>> = restDataSource.getService().getSlides()
    override fun getActions(): Observable<List<ApiServiceAction>> = restDataSource.getService().getServiceActions()
    override fun getServices(): Observable<List<ApiService>> = restDataSource.getService().getServices()
    override fun getPages(): Observable<ApiPages> = restDataSource.getService().getPages()
    override fun getNodeDoctors(): Observable<ApiDoctors> = restDataSource.getService().getNodeDoctors()
    override fun getGallery(): Observable<ApiGalleries> = restDataSource.getService().getGallery()
    override fun getChangeGallery(): Observable<List<ApiChangeFile>> = restDataSource.getService().getChangeGallery()
    override fun getDoctor(id: String): Observable<ApiCurrentDoctor> = restDataSource.getService().getDoctor(id)
    override fun getFeedback(): Observable<ApiPatients> = restDataSource.getService().getFeedback()
    override fun getPopularProblems(): Observable<List<ApiPopularProblems>> = restDataSource.getService().getPopularProblems()
    override fun getTaxonomyService(id: String): Observable<ApiTaxonomyService> = restDataSource.getService().getTaxonomyServiceById(id)
    override fun getServiceNodeDoctors(id: String): Observable<ApiDoctors> = restDataSource.getService().getServiceNodeDoctors(id)
}