package com.example.vitaura.repository

import com.example.vitaura.R
import com.example.vitaura.datasource.IDataSource
import com.example.vitaura.extensions.PriceJsonParser
import com.example.vitaura.pojo.*
import com.google.gson.*
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

    override fun getServices(): Observable<List<ServiceSubMenu>> = remoteDataSource.getServices()
            .map {
                return@map it.map { service-> ServiceSubMenu(service.fieldMobileDescription1,
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

    override fun getPages(): Observable<List<Page>> = remoteDataSource.getPages()
            .map { page->
                return@map page.pages?.map {
                    Page(it.data?.title,
                            it.data?.body?.text)
                }
            }

    override fun getNodeDoctors(): Observable<List<NodeDoctor>> = remoteDataSource.getNodeDoctors()
            .map {
                return@map it.data?.map { doctor->
                    NodeDoctor(doctor.id,
                            doctor.attributes?.body?.text,
                            doctor.attributes?.price,
                            doctor.attributes?.education?.text,
                            doctor.attributes?.information?.text,
                            doctor.attributes?.post,
                            doctor.attributes?.shortDescription?.text,
                            doctor.attributes?.specialization?.text,
                            doctor.attributes?.title,
                            doctor.attributes?.weight,
                            doctor.relations?.services?.service?.map { service-> service.id },
                            it.include?.map { it.links?.photoUri?.url.orEmpty() })
                }
            }

    override fun getGallery(): Observable<List<Gallery>> = remoteDataSource.getGallery()
            .map {
                it.include?.map { url-> Gallery(url.link?.photo?.url) }
            }

    override fun getChangeGallery(): Observable<List<ChangeFile>> = remoteDataSource.getChangeGallery()
            .map {
                it.map { file-> ChangeFile(file.url, file.title) }
            }

    override fun getDoctor(id: String): Observable<NodeDoctor> = remoteDataSource.getDoctor(id)
            .map {
                val doctor = it.data
                return@map NodeDoctor(doctor?.id,
                        doctor?.attributes?.body?.text,
                        doctor?.attributes?.price,
                        doctor?.attributes?.education?.text,
                        doctor?.attributes?.information?.text,
                        doctor?.attributes?.post,
                        doctor?.attributes?.shortDescription?.text,
                        doctor?.attributes?.specialization?.text,
                        doctor?.attributes?.title,
                        doctor?.attributes?.weight,
                        doctor?.relations?.services?.service?.map { service-> service.id },
                        it.include?.map { it.links?.photoUri?.url.orEmpty() })
            }

    override fun getFeedback(): Observable<List<Feedback>> = remoteDataSource.getFeedback()
            .map {
                return@map it.data?.map { data->
                    Feedback(data.attributes?.title, data.attributes?.text?.text)
                }
            }

    override fun getPopularProblems(): Observable<List<PopularProblems>> = remoteDataSource.getPopularProblems()
            .map {
                return@map it.map { problem-> PopularProblems(problem.url, problem.services, problem.title) }
            }

    override fun getServiceTypes(): Observable<List<ServiceType>> {
        return Observable.create{
            try {
                it.onNext(listOf(
                    ServiceType("Лицо", "face", R.drawable.face),
                    ServiceType("Тело", "body", R.drawable.body),
                    ServiceType("Волосы", "hair", R.drawable.hair),
                    ServiceType("Интимные зоны", "intim", R.drawable.intim),
                    ServiceType("Диагностика", "diagnostics", R.drawable.diagnostics)
                ))
            }catch (error: Throwable){
                it.onError(error)
            }
        }
    }

    override fun getService(id: String): Observable<Service> = remoteDataSource.getTaxonomyService(id)
        .map {
            return@map Service(it.data?.get(0)?.id,
                it.data?.get(0)?.attributes?.serviceName,
                it.data?.get(0)?.attributes?.effective?.text,
                it.data?.get(0)?.attributes?.benefits?.text,
                it.data?.get(0)?.attributes?.contraindications?.text)
        }

    override fun getDoctorsByServiceId(id: String): Observable<List<NodeDoctor>> = getService(id)
        .flatMap {
            return@flatMap remoteDataSource.getServiceNodeDoctors(it.id!!)
        }
        .map {
            return@map it.data?.map { doctor ->
                NodeDoctor(doctor.id,
                    doctor.attributes?.body?.text,
                    doctor.attributes?.price,
                    doctor.attributes?.education?.text,
                    doctor.attributes?.information?.text,
                    doctor.attributes?.post,
                    doctor.attributes?.shortDescription?.text,
                    doctor.attributes?.specialization?.text,
                    doctor.attributes?.title,
                    doctor.attributes?.weight,
                    doctor.relations?.services?.service?.map { service -> service.id },
                    it.include?.map { it.links?.photoUri?.url.orEmpty() })
            }
        }

    override fun getPrices(): Observable<Pair<MutableList<Prices>, MutableList<PricesCascade>>> = remoteDataSource.getPrices()
        .map {
            return@map PriceJsonParser.createCascadePrice(it)
        }
}