package com.example.vitaura.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vitaura.data.Result
import com.example.vitaura.repository.IRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: IRepository): ViewModel() {
    private val subscription: CompositeDisposable by lazy { CompositeDisposable() }
    private val sliderData = MutableLiveData<Result>()
    fun getSlides() = repository.getSlides()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
            sliderData.value = Result.Loading(null)
        }
        .subscribeBy(
            onNext = {
                sliderData.value = Result.Succeed(it)
            },
            onError = {
                sliderData.value = Result.Error(it)
            }
        ).addTo(subscription)

    fun getSliderData(): LiveData<Result> = sliderData

    override fun onCleared() {
        super.onCleared()
        if (!subscription.isDisposed){
            subscription.dispose()
        }
    }
}