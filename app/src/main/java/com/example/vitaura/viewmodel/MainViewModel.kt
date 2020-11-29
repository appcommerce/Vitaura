package com.example.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vitaura.data.Results
import com.example.vitaura.repository.IRepository
import com.example.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(private val repository: IRepository): BaseViewModel() {
    private val sliderData = MutableLiveData<Results>()

    fun getSlides(): LiveData<Results> {
        repository.getSlides()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {
                    sliderData.value = Results.Loading(null)
                }
                .subscribeBy(
                        onNext = {
                            sliderData.value = Results.Success(it)
                        },
                        onError = {
                            sliderData.value = Results.Error(it)
                        }
                ).addTo(subscription)
        return sliderData
    }
    fun getPages(): LiveData<Results> {
        repository.getPages()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {
                    sliderData.value = Results.Loading(null)
                }
                .subscribeBy(
                        onNext = {
                            sliderData.value = Results.Success(it)
                        },
                        onError = {
                            sliderData.value = Results.Error(it)
                        }
                ).addTo(subscription)
        return sliderData
    }
    fun getActions(): LiveData<Results>{
        repository.getActions()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {
                    sliderData.value = Results.Loading(null)
                }
                .subscribeBy(
                        onNext = {
                            sliderData.value = Results.Success(it)
                        },
                        onError = {
                            sliderData.value = Results.Error(it)
                        }
                ).addTo(subscription)
        return sliderData
    }

}