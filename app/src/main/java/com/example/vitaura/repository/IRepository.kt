package com.example.vitaura.repository

import com.example.vitaura.pojo.Action
import com.example.vitaura.pojo.Service
import com.example.vitaura.pojo.Slider
import io.reactivex.Observable

interface IRepository {
    fun getSlides(): Observable<List<Slider>>
    fun getActions(): Observable<List<Action>>
    fun getServices(): Observable<List<Service>>
    fun getServiceById(id: Int): Observable<Service>
}