package com.mahmoud_bashir.workmanagerwithroomdb.app

import android.app.Application
import com.mahmoud_bashir.workmanagerwithroomdb.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                appModule
            )
        }
    }
}