package com.example.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vitaura.pojo.Prices
import com.example.vitaura.pojo.Results
import com.example.vitaura.repository.IRepository
import com.example.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class PriceViewModel(private val repository: IRepository): BaseViewModel() {
    private val prices = MutableLiveData<Results<List<Prices>>>()

    fun getPrices(): LiveData<Results<List<Prices>>>{
        repository.getPrices()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe {
                prices.value = Results.Loading(null)
            }
            .subscribeBy(
                onNext = {
                    //prices.value = Results.Success(it)
                },
                onError = {
                    println(it.printStackTrace())
                    prices.value = Results.Error(it)
                }
            ).addTo(subscription)
        return prices
    }
}