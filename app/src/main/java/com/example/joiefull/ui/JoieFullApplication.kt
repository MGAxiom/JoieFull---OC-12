package com.example.joiefull.ui

import android.app.Application
import com.example.joiefull.di.networkModule
import com.example.joiefull.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class JoieFullApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@JoieFullApplication)
            modules(viewModelModule, networkModule)
        }
    }
}