package com.example.vitaura.data

sealed class Results{
    data class Loading(val progress: Int?): Results()
    data class Success<out T>(val data: T?): Results()
    data class Error(val throwable: Throwable?): Results()
}
