package com.example.vitaura.data

import io.reactivex.Observable

sealed class Result{
    data class Loading(private val progress: Int?): Result()
    data class Succeed<T>(private val data: T?): Result()
    data class Error(private val throwable: Throwable?): Result()
}
