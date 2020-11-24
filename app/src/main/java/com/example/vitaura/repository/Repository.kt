package com.example.vitaura.repository

import com.example.vitaura.datasource.IDataSource
import com.example.vitaura.pojo.Slider
import io.reactivex.Observable

class Repository(private val remoteDataSource: IDataSource): IRepository {
    override fun getSlides(): Observable<List<Slider>> = remoteDataSource.getSlides()
        .map {
            return@map it.map { slide-> Slider(slide.body,
                slide.fieldPhoto,
                slide.fieldPhoto2,
                slide.fieldPhoto3,
                slide.fieldPhotosMobile,
                slide.title,
                slide.viewNode)
            }
        }
}