package com.example.vitaura.repository

import com.example.vitaura.datasource.IDataSource
import com.example.vitaura.pojo.*
import io.reactivex.Observable

class Repository(private val remoteDataSource: IDataSource): IRepository {
    //+
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
    //+-
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
                NodeDoctor(doctor.attributes?.body?.text,
                           doctor.attributes?.price,
                           doctor.attributes?.education?.text,
                           doctor.attributes?.information?.text,
                           doctor.attributes?.post,
                           doctor.attributes?.shortDescription?.text,
                           doctor.attributes?.specialization?.text,
                           doctor.attributes?.title,
                           doctor.attributes?.weight,
                           doctor.relations?.services?.service?.map { service-> service.id })
            }
        }

    override fun getGallery(): Observable<List<Gallery>> = remoteDataSource.getGallery()
        .map {
            it.galleries?.map { gallery->
                Gallery(gallery.attributes?.title,
                    gallery.relationship?.galleryImageField?.files?.map { image->
                    Image(image.id, image.attributes?.uri?.url) })
            }
        }

    override fun getChangeGallery(): Observable<List<ChangeFile>> = remoteDataSource.getChangeGallery()
        .map {
            it.map { file-> ChangeFile(file.url, file.title) }
        }
    //+
    override fun getDoctors(): Observable<List<CurrentDoctor>> = remoteDataSource.getDoctors()
            .map {
                return@map it.map { doctor-> CurrentDoctor(doctor.description,
                        doctor.miniDescription,
                        doctor.name,
                        doctor.photoName,
                        doctor.spec)
                }
            }
    //+
    override fun getFeedback(): Observable<List<Feedback>> = remoteDataSource.getFeedback()
            .map {
                return@map it.data?.map { data->
                    Feedback(data.attributes?.title, data.attributes?.text?.text)
                }
            }
    //+
    override fun getPopularProblems(): Observable<List<PopularProblems>> = remoteDataSource.getPopularProblems()
            .map {
                return@map it.map { problem-> PopularProblems(problem.url, problem.services, problem.title) }
            }
}