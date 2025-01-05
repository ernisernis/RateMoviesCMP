package org.ernisernis

import android.app.Application
import org.ernisernis.ratemoviescmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class RateMoviesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@RateMoviesApplication)
        }
    }
}