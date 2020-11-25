package com.example.vitaura.repository

import com.example.vitaura.datasource.IDataSource
import com.example.vitaura.pojo.Action
import com.example.vitaura.pojo.Service
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

    override fun getActions(): Observable<List<Action>> = remoteDataSource.getActions()
        .map {
            return@map it.map { action-> Action(action.body,
                action.fieldImage,
                action.fieldImage2,
                action.fieldImagePreview,
                action.title,
                action.viewNode)
            }
        }

    override fun getServices(): Observable<List<Service>> = remoteDataSource.getServices()
        .map {
            return@map it.map { service-> Service(service.fieldMobileDescription1,
                service.fieldMobileDescription2,
                service.fieldMobileDescription3,
                service.link,
                service.parentTargetId,
                service.tid,
                service.title,
                service.type,
                service.typeWeight,
                service.weight) }
        }

    override fun getServiceById(id: Int): Observable<Service> = remoteDataSource.getServiceById(id)
        .map { service->
            return@map Service(service.fieldMobileDescription1,
                service.fieldMobileDescription2,
                service.fieldMobileDescription3,
                service.link,
                service.parentTargetId,
                service.tid,
                service.title,
                service.type,
                service.typeWeight,
                service.weight)
        }
}