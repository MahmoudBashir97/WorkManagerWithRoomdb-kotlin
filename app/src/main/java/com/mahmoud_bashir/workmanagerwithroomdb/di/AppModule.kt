package com.mahmoud_bashir.workmanagerwithroomdb.di

import androidx.room.Room
import androidx.work.*
import com.mahmoud_bashir.workmanagerwithroomdb.room.UserDao
import com.mahmoud_bashir.workmanagerwithroomdb.room.UserDatabase
import com.mahmoud_bashir.workmanagerwithroomdb.workManager.scheduledTaskWorker
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val appModule = module {

    single {
        Room.databaseBuilder(androidApplication(),
        UserDatabase::class.java,
        "user_db")
            .build()
    }

    single<UserDao>{
        val db = get<UserDatabase>()
        db.userDao()
    }
}
