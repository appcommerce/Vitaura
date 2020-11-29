package com.example.vitaura.viewmodel.base

import androidx.lifecycle.ViewModel
import com.example.vitaura.rx.IReactiveX
import com.example.vitaura.rx.ReactiveX
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(val scheduler: IReactiveX = ReactiveX(),
                    val subscription: CompositeDisposable = CompositeDisposable()
): ViewModel() {
    override fun onCleared() {
        super.onCleared()
        if (!subscription.isDisposed){
            subscription.dispose()
        }
    }
}