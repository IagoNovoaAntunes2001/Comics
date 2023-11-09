package com.example.comics.di.modules

import com.example.comics.data.converter.ComicDataConverter
import org.koin.dsl.module

val convertersModule = module {
    single { provideUserDataConverter() }
}

private fun provideUserDataConverter() = ComicDataConverter()
