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

    override fun getServiceActions(): Observable<List<ServiceAction>> = remoteDataSource.getServiceActions()
            .map {
                return@map it.map { action-> ServiceAction(action.body,
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
                            it.data?.body?.text, null)
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

    override fun getChangeGallery(): Observable<List<Gallery>> = remoteDataSource.getChangeGallery()
            .map {
                it.map { file-> Gallery(file.url) }
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

    override fun getVideoAlbums(): Observable<List<VideoAlbums>> = remoteDataSource.getVideoAlbums()
            .map {
                return@map it.data?.map { video-> VideoAlbums(video.id,
                        video.attributes?.title,
                        video.attributes?.youtube?.url,
                        video.attributes?.youtube?.id)
                }
            }

    override fun getVideo(id: String): Observable<VideoAlbums> = remoteDataSource.getVideo(id)
            .map {
                return@map VideoAlbums(it.data?.id,
                        it.data?.attributes?.title,
                it.data?.attributes?.youtube?.url,
                it.data?.attributes?.youtube?.id)
            }

    override fun getActions(): Observable<List<Action>> = remoteDataSource.getAllActions()
        .map {
                val actions = it.data?.map { action->
                    Action(action.id, action.attributes?.title, action.attributes?.body?.value, null, null)
                }?.toMutableList() ?: mutableListOf()
                for (i in actions.indices){
                    actions[i].imgUrlMin = it.photos?.get(i)?.links?.imageMin?.url
                    actions[i].imgUrlMax = it.photos?.get(i)?.links?.imageMax?.url
                }
            return@map actions
        }

    override fun getActionById(id: String): Observable<Action> = remoteDataSource.getActionById(id)
        .map {
            return@map Action(it.data?.id,
                it.data?.attributes?.title,
                it.data?.attributes?.body?.value,
                it.photos?.get(0)?.links?.imageMax?.url,
                it.photos?.get(0)?.links?.imageMin?.url)
        }

    override fun getPage(id: String): Observable<Page> = remoteDataSource.getPage(id)
        .map {
            val docs = it.photos?.map { url-> Doc(null, url.links?.image?.url) }?.toMutableList() ?: mutableListOf()
            val titles = it.data?.relationships?.files?.data?.map{ file-> file.meta?.description } ?: listOf()
            for (i in titles.indices){
                docs[i].title = titles[i]
            }
            return@map Page(it.data?.attributes?.title, it.data?.attributes?.body?.description, docs)
        }
}