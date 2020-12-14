package com.example.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vitaura.pojo.CurrentDoctor
import com.example.vitaura.pojo.Results
import com.example.vitaura.repository.IRepository
import com.example.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class DoctorsViewModel(private val repository: IRepository): BaseViewModel() {
    private val doctors = MutableLiveData<Results<List<CurrentDoctor>>>()

    fun getDoctors(): LiveData<Results<List<CurrentDoctor>>> {
        repository.getDoctors()
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
}