package com.rozoomcool.testapp

import android.app.Application
import com.rozoomcool.testapp.di.appModule
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}