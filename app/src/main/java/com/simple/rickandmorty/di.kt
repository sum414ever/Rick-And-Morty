package com.simple.rickandmorty

import androidx.room.Room
import com.simple.rickandmorty.data.db.HeroDatabase
import com.simple.rickandmorty.data.net.*
import com.simple.rickandmorty.domain.AppRepository
import com.simple.rickandmorty.ui.heroes.HeroesViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter


val apiModule = module {
    single<HeroDatabase> {
        Room.databaseBuilder(androidContext(), HeroDatabase::class.java, "database")
            .build()
    }
    single<OkHttpClient> { createOkHttpClient() }
    single<Converter.Factory> { createGsonConverterFactory() }
    single<ApiService> { createWebService(get(), get(), ApiService.BASE_URL) }
    single<AppRepository> { AppRepositoryImpl(get(), get()) }
    viewModel { HeroesViewModel(get()) }

}