package com.example.vitaura.repository

import com.example.vitaura.pojo.*
import io.reactivex.Observable

interface IRepository {
    fun getSlides(): Observable<List<Slider>>
    fun getServiceActions(): Observable<List<ServiceAction>>
    fun getServices(): Observable<List<ServiceSubMenu>>
    fun getPages(): Observable<List<Page>>
    fun getNodeDoctors(): Observable<List<NodeDoctor>>
    fun getGallery(): Observable<List<Gallery>>
    fun getChangeGallery(): Observable<List<Gallery>>
    fun getDoctor(id: String): Observable<NodeDoctor>
    fun getFeedback(): Observable<List<Feedback>>
    fun getPopularProblems(): Observable<List<PopularProblems>>
    fun getServiceTypes(): Observable<List<ServiceType>>
    fun getService(id: String): Observable<Service>
    fun getDoctorsByServiceId(id: String): Observable<List<NodeDoctor>>
    fun getPrices(): Observable<Pair<MutableList<Prices>, MutableList<PricesCascade>>>
    fun getVideoAlbums(): Observable<List<VideoAlbums>>
    fun getVideo(id: String): Observable<VideoAlbums>
    fun getActions(): Observable<List<Action>>
}