package com.example.vitaura.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vitaura.pojo.Action
import com.example.vitaura.pojo.Results
import com.example.vitaura.repository.IRepository
import com.example.vitaura.viewmodel.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ActionViewModel(private val repository: IRepository): BaseViewModel() {
    private val actions = MutableLiveData<Results<List<Action>>>()
    private val action = MutableLiveData<Results<Action>>()
    var actionId = ""

    fun getActions(): LiveData<Results<List<Action>>>{
        repository.getActions()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe {
                actions.value = Results.Loading(null)
            }
            .subscribeBy(
                onNext = {
                    actions.value = Results.Success(it)
                },
                onError = {
                    actions.value = Results.Error(it)
                }
            ).addTo(subscription)
        return actions
    }

    fun getAction(id: String): LiveData<Results<Action>>{
        repository.getActionById(id)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe {
                action.value = Results.Loading(null)
            }
            .subscribeBy(
                onNext = {
                    action.value = Results.Success(it)
                },
                onError = {
                    action.value = Results.Error(it)
                }
            ).addTo(subscription)
        return action
    }
}