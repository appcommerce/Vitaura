package com.appcommerce.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appcommerce.vitaura.pojo.Prices
import com.appcommerce.vitaura.pojo.PricesCascade
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.repository.IRepository
import com.appcommerce.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class PriceViewModel(private val repository: IRepository): BaseViewModel() {
    private val prices = MutableLiveData<Results<Pair<MutableList<Prices>, MutableList<PricesCascade>>>>()

    fun getPrices(): LiveData<Results<Pair<MutableList<Prices>, MutableList<PricesCascade>>>>{
        repository.getPrices()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe {
                prices.value = Results.Loading(null)
            }
            .subscribeBy(
                onNext = {
                    prices.value = Results.Success(it)
                },
                onError = {
                    prices.value = Results.Error(it)
                }
            ).addTo(subscription)
        return prices
    }
}