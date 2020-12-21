package com.example.vitaura.di

import com.example.vitaura.datasource.IDataSource
import com.example.vitaura.datasource.remote.BaseInterceptor
import com.example.vitaura.datasource.remote.RemoteDataSource
import com.example.vitaura.datasource.remote.RetrofitProvider
import com.example.vitaura.repository.IRepository
import com.example.vitaura.repository.Repository
import com.example.vitaura.viewmodel.DoctorsViewModel
import com.example.vitaura.viewmodel.MainViewModel
import com.example.vitaura.viewmodel.PriceViewModel
import com.example.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { BaseInterceptor }
    single { RetrofitProvider(get()) }
    single<IDataSource>(named("REMOTE")) {RemoteDataSource(get())}
}

val repositoryModule = module {
    single<IRepository> { Repository(get(named("REMOTE"))) }
}

val viewModelsModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DoctorsViewModel(get()) }
    viewModel { ServiceViewModel(get()) }
    viewModel { PriceViewModel(get()) }
}