package com.example.vitaura.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReactiveX: IReactiveX {
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}