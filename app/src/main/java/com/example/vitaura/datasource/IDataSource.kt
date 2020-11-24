package com.example.vitaura.datasource

import com.example.vitaura.data.ApiSlider
import io.reactivex.Observable

interface IDataSource {
    fun getSlides(): Observable<List<ApiSlider>>
}