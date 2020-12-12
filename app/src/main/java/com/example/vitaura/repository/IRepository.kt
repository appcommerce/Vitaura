package com.example.vitaura.repository

import com.example.vitaura.pojo.*
import io.reactivex.Observable

interface IRepository {
    fun getSlides(): Observable<List<Slider>>
    fun getActions(): Observable<List<Action>>
    fun getServices(): Observable<List<Service>>
    fun getServiceById(id: Int): Observable<Service>
    fun getPages(): Observable<List<Page>>
    fun getNodeDoctors(): Observable<List<NodeDoctor>>
    fun getGallery(): Observable<List<Gallery>>
    fun getChangeGallery(): Observable<List<ChangeFile>>
    fun getDoctors(): Observable<List<CurrentDoctor>>
    fun getFeedback(): Observable<List<Feedback>>
}