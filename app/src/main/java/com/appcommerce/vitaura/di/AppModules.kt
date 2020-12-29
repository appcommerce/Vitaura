package com.appcommerce.vitaura.di

import com.appcommerce.vitaura.datasource.IDataSource
import com.appcommerce.vitaura.datasource.remote.BaseInterceptor
import com.appcommerce.vitaura.datasource.remote.RemoteDataSource
import com.appcommerce.vitaura.datasource.remote.RetrofitProvider
import com.appcommerce.vitaura.repository.IRepository
import com.appcommerce.vitaura.repository.Repository
import com.appcommerce.vitaura.viewmodel.*
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
    viewModel { MediaViewModel(get()) }
    viewModel { ActionViewModel(get()) }
    viewModel { AboutViewModel(get()) }
}