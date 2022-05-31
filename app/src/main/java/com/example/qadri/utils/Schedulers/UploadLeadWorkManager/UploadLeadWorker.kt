package com.example.qadri.utils.Schedulers.UploadLeadWorkManager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.qadri.mvvm.repository.UserRepository
import com.example.qadri.mvvm.room.DAOAccess
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class UploadLeadWorker @AssistedInject constructor(
    @Assisted var appContext: Context,
    @Assisted var workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    @Inject lateinit var userRepository: UserRepository
    @Inject lateinit var daoAccess: DAOAccess
//    var apiListener: ApiListener? = null

    companion object {
        private const val TAG = "UploadWorker"
    }

    override suspend fun doWork(): Result {
        Log.i(TAG, "Fetching Data from Remote host")

//        return try {
//            val leadData = daoAccess.getUnSyncLeadData()
//            if (leadData.isNotEmpty()) {
//                leadData.forEach {
//                    val response = userRepository.addLead(it.getCustomerDetail()).execute()
//                    if (response.body() != null && response.body()?.lead_id != null) {
//                        daoAccess.updateLeadData(response.body()?.lead_id.toString(), it.local_lead_id.toString())
//                        daoAccess.updateCheckInLeadStatus(response.body()?.lead_id.toString(), it.local_lead_id.toString())
//                    } else {
//                        Result.retry()
//                    }
//                }
//            }
//
//            Result.success()
//
//        } catch (e: Throwable) {
//            e.printStackTrace()
//            // Technically WorkManager will return Result.failure()
//            // but it's best to be explicit about it.
//            // Thus if there were errors, we're return FAILURE
//            Log.e(TAG, "Error fetching data", e)
//            Result.failure()
//        }

        return Result.failure()
    }
}