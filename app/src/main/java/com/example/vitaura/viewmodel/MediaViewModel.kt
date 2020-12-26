package com.example.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vitaura.pojo.Gallery
import com.example.vitaura.pojo.Results
import com.example.vitaura.repository.IRepository
import com.example.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MediaViewModel(private val repository: IRepository): BaseViewModel() {
    private val photos = MutableLiveData<Results<List<Gallery>>>()
    var albumId = ""

    fun getClinicPhotos(): LiveData<Results<List<Gallery>>>{
        repository.getGallery()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {
                    photos.value = Results.Loading(null)
                }.subscribeBy(
                        onNext = {
                            photos.value = Results.Success(it)
                        },
                        onError = {
                            photos.value = Results.Error(it)
                        }
                ).addTo(subscription)
        return photos
    }

    fun getChangeGallery(): LiveData<Results<List<Gallery>>>{
        repository.getChangeGallery()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe {
                    photos.value = Results.Loading(null)
                }.subscribeBy(
                        onNext = {
                            photos.value = Results.Success(it)
                        },
                        onError = {
                            photos.value = Results.Error(it)
                        }
                ).addTo(subscription)
        return photos
    }
}