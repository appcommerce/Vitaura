package com.appcommerce.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appcommerce.vitaura.extensions.Constants
import com.appcommerce.vitaura.pojo.AboutSlide
import com.appcommerce.vitaura.pojo.Page
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.repository.IRepository
import com.appcommerce.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class AboutViewModel(private val repository: IRepository): BaseViewModel() {
    private val aboutImages = MutableLiveData<List<AboutSlide>>()
    private val infoPage = MutableLiveData<Results<Page>>()
    private val docPage = MutableLiveData<Results<Page>>()
    private val lawPage = MutableLiveData<Results<Page>>()

    fun getAboutImages(): LiveData<List<AboutSlide>>{
        aboutImages.value = listOf(AboutSlide("${Constants.SERVER_URL}/sites/default/files/inline-images/_MG_0031.jpg"),
        AboutSlide("${Constants.SERVER_URL}/sites/default/files/inline-images/_MG_0174.jpg")
        )
        return aboutImages
    }

    fun getPage(id: String, tag: String): LiveData<Results<Page>>{
        repository.getPage(id)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe {
                when(tag){
                    "info" -> infoPage.value = Results.Loading(null)
                    "doc" -> docPage.value = Results.Loading(null)
                    "law" -> lawPage.value = Results.Loading(null)
                }
            }.subscribeBy(
                onNext = {
                    when(tag){
                        "info" -> infoPage.value = Results.Success(it)
                        "doc" -> docPage.value = Results.Success(it)
                        "law" -> lawPage.value = Results.Success(it)
                    }
                },
                onError = {
                    when(tag){
                        "info" -> infoPage.value = Results.Error(it)
                        "doc" -> docPage.value = Results.Error(it)
                        "law" -> lawPage.value = Results.Error(it)
                    }
                }
            ).addTo(subscription)
        return when(tag){
            "info" -> infoPage
            "doc" -> docPage
            "law" -> lawPage
            else -> infoPage
        }
    }
}