package com.example.comics.di

import com.example.comics.di.modules.convertersModule
import com.example.comics.di.modules.remoteModule
import com.example.comics.di.modules.repositoriesModule
import com.example.comics.di.modules.viewModelsModule

val appModules = listOf(
    remoteModule,
    repositoriesModule,
    viewModelsModule,
    convertersModule
)
