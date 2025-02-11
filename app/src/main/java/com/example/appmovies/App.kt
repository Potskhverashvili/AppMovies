package com.example.appmovies

import android.app.Application
import com.example.appmovies.di.repositoryModule
import com.example.appmovies.di.firebaseModule
import com.example.appmovies.di.useCaseModule
import com.example.appmovies.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                firebaseModule,
                repositoryModule,
                useCaseModule,
                viewModelsModule
            )
        }

    }
}