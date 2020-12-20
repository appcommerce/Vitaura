package com.example.vitaura.repository

import com.example.vitaura.pojo.*
import io.reactivex.Observable

interface IRepository {
    fun getSlides(): Observable<List<Slider>>
    fun getActions(): Observable<List<Action>>
    fun getServices(): Observable<List<ServiceSubMenu>>
    fun getPages(): Observable<List<Page>>
    fun getNodeDoctors(): Observable<List<NodeDoctor>>
    fun getGallery(): Observable<List<Gallery>>
    fun getChangeGallery(): Observable<List<ChangeFile>>
    fun getDoctor(id: String): Observable<NodeDoctor>
    fun getFeedback(): Observable<List<Feedback>>
    fun getPopularProblems(): Observable<List<PopularProblems>>
    fun getServiceTypes(): Observable<List<ServiceType>>
    fun getService(id: String): Observable<Service>
}