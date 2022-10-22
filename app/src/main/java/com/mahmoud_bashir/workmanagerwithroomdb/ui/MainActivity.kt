package com.mahmoud_bashir.workmanagerwithroomdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.mahmoud_bashir.workmanagerwithroomdb.R
import com.mahmoud_bashir.workmanagerwithroomdb.room.UserDatabase
import com.mahmoud_bashir.workmanagerwithroomdb.room.UserEntity
import com.mahmoud_bashir.workmanagerwithroomdb.workManager.scheduledTaskWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    public val db : UserDatabase by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scheduledWorker = PeriodicWorkRequestBuilder<scheduledTaskWorker>(15,TimeUnit.MINUTES)
            .setConstraints(createConstraints())
            .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork("Smart work", ExistingPeriodicWorkPolicy.REPLACE, scheduledWorker)


//        val scheduledWorker = OneTimeWorkRequestBuilder<scheduledTaskWorker>()
//        val workManager = WorkManager.getInstance(applicationContext)
//        val continuation = workManager.beginWith(scheduledWorker.build())
//        continuation.enqueue()
//        lifecycleScope.launch(Dispatchers.IO){
//            db.userDao().insertUser(UserEntity(0,"Mahmoud",25))
//        }
    }

    fun createConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)  // if connected to WIFI
        // other values(NOT_REQUIRED, CONNECTED, NOT_ROAMING, METERED)
        .setRequiresBatteryNotLow(true)                 // if the battery is not low
        .setRequiresStorageNotLow(true)                 // if the storage is not low
        .build()

}