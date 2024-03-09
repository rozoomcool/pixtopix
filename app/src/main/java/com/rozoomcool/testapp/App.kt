package com.rozoomcool.testapp

import android.app.Application
import com.rozoomcool.testapp.di.appModule
import com.rozoomcool.testapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, viewModelModule)
        }
    }
}