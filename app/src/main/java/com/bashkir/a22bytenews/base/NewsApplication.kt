package com.bashkir.a22bytenews.base

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.bashkir.a22bytenews.data.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)

        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@NewsApplication)
            modules(newsModule)
        }
    }
}