package com.example.comics.di.modules

import com.example.comics.data.repository.ComicRepositoryImpl
import com.example.comics.domain.service.ComicService
import com.example.comics.domain.repository.ComicRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<ComicRepository> { provideComicRepository(get()) }
}

private fun provideComicRepository(comicService: ComicService) =
    ComicRepositoryImpl(comicService = comicService)