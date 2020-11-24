package com.example.vitaura.repository

import com.example.vitaura.pojo.Slider
import io.reactivex.Observable

interface IRepository {
    fun getSlides(): Observable<List<Slider>>
}