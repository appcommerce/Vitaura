package com.appcommerce.vitaura.viewmodel.base

import androidx.lifecycle.ViewModel
import com.appcommerce.vitaura.rx.IReactiveX
import com.appcommerce.vitaura.rx.ReactiveX
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