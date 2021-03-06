package com.example.qadri.utils.Schedulers.LocationWorkManager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.qadri.mvvm.model.generic.GenericMsgResponse
import com.example.qadri.mvvm.network.ApiListener
import com.example.qadri.mvvm.repository.UserRepository
import com.example.qadri.mvvm.room.DAOAccess
import com.example.qadri.utils.GsonFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class LocationWorker @AssistedInject constructor(
    @Assisted var appContext: Context,
    @Assisted var workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    @Inject lateinit var userRepository: UserRepository
    @Inject lateinit var daoAccess: DAOAccess
    var apiListener: ApiListener? = null

    override fun doWork(): Result {
        Log.i(TAG, "Fetching Data from Remote host")
//        return try {
//            val userList = daoAccess.getUserLocation()
//            val call = userRepository.uploadUserLocation(userList)
//            val response = GsonFactory.getConfiguredGson()?.fromJson(call.value, GenericMsgResponse::class.java)
//
//            if (response?.message == "Successful") {
//                   daoAccess.deleteUserLocation()
//                Result.success()
//            } else {
//                Result.retry()
//            }
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

    companion object {
        private val TAG = "LocationWorker"
    }

}