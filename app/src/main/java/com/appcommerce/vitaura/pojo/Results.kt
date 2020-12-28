package com.appcommerce.vitaura.pojo

sealed class Results<out T>{
    data class Loading(val progress: Int?): Results<Nothing>()
    data class Success<T>(val data: T?): Results<T>()
    data class Error(val throwable: Throwable?): Results<Nothing>()
}
