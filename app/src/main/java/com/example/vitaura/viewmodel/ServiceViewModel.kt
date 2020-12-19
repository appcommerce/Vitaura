package com.example.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vitaura.pojo.Results
import com.example.vitaura.pojo.Service
import com.example.vitaura.pojo.ServiceType
import com.example.vitaura.repository.IRepository
import com.example.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ServiceViewModel(private val repository: IRepository): BaseViewModel() {
    private val services = MutableLiveData<Results<List<Service>>>()
    private val serviceTypes = MutableLiveData<Results<List<ServiceType>>>()

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

    fun getServices(): LiveData<Results<List<Service>>>{
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
}