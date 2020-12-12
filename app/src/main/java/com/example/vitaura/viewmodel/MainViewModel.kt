package com.example.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vitaura.pojo.Results
import com.example.vitaura.pojo.Feedback
import com.example.vitaura.pojo.Slider
import com.example.vitaura.repository.IRepository
import com.example.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(private val repository: IRepository): BaseViewModel() {
    private val sliderData = MutableLiveData<Results<List<Slider>>>()
    private val feedsData = MutableLiveData<Results<List<Feedback>>>()

    fun getSlides(): LiveData<Results<List<Slider>>> {
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

//    fun getPages(): LiveData<Results> {
//        repository.getPages()
//                .subscribeOn(scheduler.io())
//                .observeOn(scheduler.ui())
//                .doOnSubscribe {
//                    sliderData.value = Results.Loading(null)
//                }
//                .subscribeBy(
//                        onNext = {
//                            sliderData.value = Results.Success(it)
//                        },
//                        onError = {
//                            sliderData.value = Results.Error(it)
//                        }
//                ).addTo(subscription)
//        return sliderData
//    }
//    fun getActions(): LiveData<Results>{
//        repository.getActions()
//                .subscribeOn(scheduler.io())
//                .observeOn(scheduler.ui())
//                .doOnSubscribe {
//                    sliderData.value = Results.Loading(null)
//                }
//                .subscribeBy(
//                        onNext = {
//                            sliderData.value = Results.Success(it)
//                        },
//                        onError = {
//                            sliderData.value = Results.Error(it)
//                        }
//                ).addTo(subscription)
//        return sliderData
//    }

    fun getFeedback(): LiveData<Results<List<Feedback>>>{
        repository.getFeedback()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {
                    feedsData.value = Results.Loading(null)
                }
                .subscribeBy(
                        onNext = {
                            feedsData.value = Results.Success(it)
                        },
                        onError = {
                            feedsData.value = Results.Error(it)
                        }
                ).addTo(subscription)
        return feedsData
    }
}