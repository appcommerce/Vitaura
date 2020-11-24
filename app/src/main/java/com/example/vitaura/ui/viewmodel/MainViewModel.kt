package com.example.vitaura.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vitaura.data.Result
import com.example.vitaura.pojo.Slider
import com.example.vitaura.repository.IRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: IRepository): ViewModel() {
    private val subscription: CompositeDisposable = CompositeDisposable()
    private val sliderData = MutableLiveData<Result>()
    fun getSlides(): LiveData<Result> {
        repository.getSlides()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    sliderData.value = Result.Loading(null)
                }
                .subscribeBy(
                        onNext = {
                            sliderData.value = Result.Success(it)
                        },
                        onError = {
                            sliderData.value = Result.Error(it)
                        }
                ).addTo(subscription)
        return sliderData
    }

    override fun onCleared() {
        super.onCleared()
        if (!subscription.isDisposed){
            subscription.dispose()
        }
    }
}