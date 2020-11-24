package com.example.vitaura.data

sealed class Result{
    data class Loading(val progress: Int?): Result()
    data class Success<out T>(val data: T?): Result()
    data class Error(val throwable: Throwable?): Result()
}
