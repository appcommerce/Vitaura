package com.appcommerce.vitaura.datasource.remote

import com.appcommerce.vitaura.data.*
import com.appcommerce.vitaura.datasource.IDataSource
import com.google.gson.JsonObject
import io.reactivex.Observable

class RemoteDataSource(private val restDataSource: RetrofitProvider): IDataSource{
    override fun getSlides(): Observable<List<ApiSlider>> = restDataSource.getService().getSlides()
    override fun getServiceActions(): Observable<List<ApiServiceAction>> = restDataSource.getService().getServiceActions()
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
    override fun getPrices(): Observable<JsonObject> = restDataSource.getService().getPrices()
    override fun getVideoAlbums(): Observable<ApiVideoAlbums> = restDataSource.getService().getVideoAlbums()
    override fun getVideo(id: String): Observable<ApiVideo> = restDataSource.getService().getVideo(id)
    override fun getAllActions(): Observable<ApiActions> = restDataSource.getService().getActions()
    override fun getActionById(id: String): Observable<ApiAction> = restDataSource.getService().getActionById(id)
    override fun getPage(id: String): Observable<ApiPage> = restDataSource.getService().getPage(id)
    override fun getPriceByService(page: String): Observable<JsonObject> = restDataSource.getService().getPriceByService(page)
}