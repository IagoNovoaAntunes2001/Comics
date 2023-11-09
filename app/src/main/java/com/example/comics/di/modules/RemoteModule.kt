package com.example.comics.di.modules

import com.example.comics.BuildConfig
import com.example.comics.domain.service.ComicService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single<ComicService> { provideRemoteInstance() }
}

private fun provideRemoteInstance() = Retrofit.Builder()
    .baseUrl(BuildConfig.API_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(ComicService::class.java)
