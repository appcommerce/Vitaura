package com.appcommerce.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.pojo.Service
import com.appcommerce.vitaura.pojo.ServiceSubMenu
import com.appcommerce.vitaura.pojo.ServiceType
import com.appcommerce.vitaura.repository.IRepository
import com.appcommerce.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ServiceViewModel(private val repository: IRepository): BaseViewModel() {
    private val services = MutableLiveData<Results<List<ServiceSubMenu>>>()
    private val serviceTypes = MutableLiveData<Results<List<ServiceType>>>()
    private val service = MutableLiveData<Results<Service>>()
    var serviceTypeAlias: String? = null
    var serviceTypeImg: Int? = null
    var serviceTypeName: String? = null
    var serviceTid: String? = null
    var servicePage: String? = null
    fun getServiceTypes() : LiveData<Results<List<ServiceType>>>{
        repository.getServiceTypes()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe {
                serviceTypes.value = Results.Loading(null)
            }
            .subscribeBy(
                onNext = {
                    serviceTypes.value = Results.Success(it)
                },
                onError = {
                    serviceTypes.value = Results.Error(it)
                }
            ).addTo(subscription)
        return serviceTypes
    }

    fun getServices(): LiveData<Results<List<ServiceSubMenu>>>{
        repository.getServices()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe {
                services.value = Results.Loading(null)
            }
            .subscribeBy(
                onNext = {
                    services.value = Results.Success(it)
                },
                onError = {
                    services.value = Results.Error(it)
                }
            ).addTo(subscription)
        return services
    }

    fun getServiceById(id: String): LiveData<Results<Service>>{
        repository.getService(id)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe {
                service.value = Results.Loading(null)
            }
            .subscribeBy(
                onNext = {
                    service.value = Results.Success(it)
                },
                onError = {
                    service.value = Results.Error(it)
                }
            ).addTo(subscription)
        return service
    }
}