package com.example.qadri.utils.Schedulers.UploadCheckInWorkManager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.qadri.mvvm.repository.UserRepository
import com.example.qadri.mvvm.room.DAOAccess
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class UploadCheckInWorker @AssistedInject constructor(
    @Assisted var context: Context,
    @Assisted var workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    @Inject
    lateinit var userRepository: UserRepository
    @Inject
    lateinit var daoAccess: DAOAccess
    companion object {
        private const val TAG = "UploadCheckInWorker"
    }

    override suspend fun doWork(): Result {

//        return try {
//            Log.i(TAG, "Fetching Data from Remote host")
//
//            val checkInData = daoAccess.getUnSyncedCheckInData("false")
//            if (checkInData.isNotEmpty()) {
//                checkInData.forEach {
//                    val response = userRepository.addLeadCheckin(it.getCheckInData()).execute()
//                    if (response.body() != null) {
//                        daoAccess.updateCheckInStatus(it.lead_id!!)
//                        daoAccess.updateLeadStatus(it.lead_id)
//                        Result.success()
//                    } else {
//                        Result.retry()
//                    }
//                }
//            }
//
//            return Result.success()
//
//        } catch (e: Throwable) {
//            e.printStackTrace()
//            // Technically WorkManager will return Result.failure()
//            // but it's best to be explicit about it.
//            // Thus if there were errors, we're return FAILURE
//            Log.e(TAG, "Error fetching data", e)
//            Result.failure()
//        }
//    }

        return Result.failure()
    }

}


