package com.example.comics.ui

import android.app.Application
import com.example.comics.di.appModules
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}
