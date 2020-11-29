package com.example.vitaura.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vitaura.data.Results
import com.example.vitaura.repository.IRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: IRepository): ViewModel() {
    private val subscription: CompositeDisposable = CompositeDisposable()
    private val sliderData = MutableLiveData<Results>()

    fun getSlides(): LiveData<Results> {
        repository.getSlides()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

    override fun onCleared() {
        super.onCleared()
        if (!subscription.isDisposed){
            subscription.dispose()
        }
    }
}