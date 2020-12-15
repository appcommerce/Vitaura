package com.example.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vitaura.pojo.NodeDoctor
import com.example.vitaura.pojo.Results
import com.example.vitaura.repository.IRepository
import com.example.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class DoctorsViewModel(private val repository: IRepository): BaseViewModel() {
    private val doctors = MutableLiveData<Results<List<NodeDoctor>>>()
    private val doctor = MutableLiveData<Results<NodeDoctor>>()
    var doctorId: String? = null
    fun getDoctors(): LiveData<Results<List<NodeDoctor>>> {
        repository.getNodeDoctors()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {
                    doctors.value = Results.Loading(null)
                }.subscribeBy(
                        onNext = {
                            doctors.value = Results.Success(it)
                        },
                        onError = {
                            doctors.value = Results.Error(it)
                        }
                ).addTo(subscription)
        return doctors
    }

    fun getDoctor(id: String): LiveData<Results<NodeDoctor>> {
        repository.getDoctor(id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {
                    doctor.value = Results.Loading(null)
                }.subscribeBy(
                        onNext = {
                            doctor.value = Results.Success(it)
                        },
                        onError = {
                            doctor.value = Results.Error(it)
                        }
                ).addTo(subscription)
        return doctor
    }
}