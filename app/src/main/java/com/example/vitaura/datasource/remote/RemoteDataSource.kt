package com.example.vitaura.datasource.remote

import com.example.vitaura.data.ApiSlider
import com.example.vitaura.datasource.IDataSource
import io.reactivex.Observable

class RemoteDataSource(private val restDataSource: RetrofitProvider): IDataSource{
    override fun getSlides(): Observable<List<ApiSlider>> = restDataSource.getService().getSlides()
}