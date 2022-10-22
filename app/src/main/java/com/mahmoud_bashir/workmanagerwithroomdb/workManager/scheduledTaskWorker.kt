package com.mahmoud_bashir.workmanagerwithroomdb.workManager

import android.content.Context
import android.icu.util.Calendar
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mahmoud_bashir.workmanagerwithroomdb.room.UserDao
import com.mahmoud_bashir.workmanagerwithroomdb.room.UserDatabase
import com.mahmoud_bashir.workmanagerwithroomdb.room.UserEntity
import com.mahmoud_bashir.workmanagerwithroomdb.ui.MainActivity
import kotlinx.coroutines.MainScope
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.SimpleDateFormat

class scheduledTaskWorker(private val context: Context, params:WorkerParameters):
    CoroutineWorker(context,params) , KoinComponent {

    private val dao:UserDao by inject()

    override suspend fun doWork(): Result {


        return try {

                val list = dao
                    .getAllUsers().toList()

                Log.d("????", "successOp : ${list.size}")

            Result.success()

        }catch(throwable:Throwable){
            throwable.printStackTrace()
            Result.failure()
        }
    }
}