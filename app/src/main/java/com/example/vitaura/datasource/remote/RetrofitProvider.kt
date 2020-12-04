package com.example.vitaura.datasource.remote

import com.example.vitaura.extensions.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider(private val interceptor: BaseInterceptor) {
    private val baseUrl = Constants.SERVER_URL
    fun getService(): IRestRequests = getRetrofit(interceptor)
            .create(IRestRequests::class.java)
    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }
    private fun getRetrofit(interceptor: Interceptor): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.client(createOkHttpClient(interceptor))
            .build()
}