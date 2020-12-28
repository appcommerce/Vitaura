package com.appcommerce.vitaura.rx

import io.reactivex.Scheduler

interface IReactiveX {
    fun ui(): Scheduler
    fun io(): Scheduler
}