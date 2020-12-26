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

    fun getChampionshipPhotos(): LiveData<Results<List<Gallery>>>{
        photos.value = Results.Success(listOf(
                Gallery("/sites/default/files/inline-images/_MG_2249%20copy.jpg"),
                Gallery("/sites/default/files/inline-images/_MG_2200%20copy.jpg"),
                Gallery("/sites/default/files/inline-images/_MG_2129%20copy.jpg"),
                Gallery("/sites/default/files/inline-images/_MG_2135%20copy.jpg"),
                Gallery("/sites/default/files/inline-images/_MG_2167%20copy.jpg"),
                Gallery("/sites/default/files/inline-images/_MG_2215%20copy.jpg"),
                Gallery("/sites/default/files/inline-images/_MG_2216%20copy.jpg"),
                Gallery("/sites/default/files/inline-images/_MG_2069%20copy.jpg"),
                Gallery("/sites/default/files/inline-images/_MG_2235%20copy.jpg")
        ))
        return photos
    }
}