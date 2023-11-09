package com.example.comics.di.modules

import com.example.comics.data.converter.ComicDataConverter
import com.example.comics.domain.repository.ComicRepository
import com.example.comics.ui.comics.viewmodel.ComicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { provideMainViewModel(get(), get()) }
}

private fun provideMainViewModel(
    comicRepository: ComicRepository,
    comicDataConverter: ComicDataConverter
) =
    ComicsViewModel(comicRepository = comicRepository, comicsDataConverter = comicDataConverter)
