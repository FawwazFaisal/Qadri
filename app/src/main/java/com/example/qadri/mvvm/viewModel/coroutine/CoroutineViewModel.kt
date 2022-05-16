package com.example.qadri.mvvm.viewModel.coroutine

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qadri.mvvm.model.customer.CustomerResponse
import com.example.qadri.mvvm.model.lov.LovResponse
import com.example.qadri.mvvm.model.portfolio.PortfolioResponse
import com.example.qadri.mvvm.model.syncModel.SyncModel
import com.example.qadri.mvvm.repository.UserRepository
import com.example.qadri.mvvm.room.RoomHelper
import com.example.qadri.utils.SharedPrefManager
import kotlinx.coroutines.*
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoroutineViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    var handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + handler)

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager

    @Inject
    lateinit var roomHelper: RoomHelper


    @SuppressLint("NewApi")
    fun getLOV(): MutableLiveData<SyncModel> {
        val data = MutableLiveData<SyncModel>()
        CoroutineScope(Dispatchers.IO).launch {
            supervisorScope {
                try {

                    val format = SimpleDateFormat("yyyy-MM-dd")
                    val fromDate: Calendar = Calendar.getInstance()
                    val toDate = format.format(Date())
                    fromDate.add(Calendar.MONTH, -1)

                    Log.i("xxFromDate", format.format(fromDate.time))
                    Log.i("xxToDate", toDate.toString())

                    val callLov = async { userRepository.getLovs() }
//                    val callCustomers = async { userRepository.getCustomers() }
                    val visitCall = async {
                        userRepository.getCustomers().execute()
                    }
//                    val portfolio = async { userRepository.getPortfolio() }

//                    val customersResponse: CustomerResponse? = try {
//                        callCustomers.await()
//                    } catch (ex: Exception) {
//                        null
//                    }

                    val lovResponse: LovResponse? = try {
                        callLov.await()
                    } catch (ex: Exception) {
                        null
                    }

                    val visitCallResponse: Response<CustomerResponse>? = try {
                        visitCall.await()
                    } catch (ex: Exception) {
                        null
                    }
//                    val portfolioResponse: PortfolioResponse? = try {
//                        portfolio.await()
//                    } catch (ex: Exception) {
//                        null
//                    }

                    if (lovResponse != null) {
                            data.postValue(
                                SyncModel(
                                    lovResponse,
                                    visitCallResponse?.body()!!
                                )
                            )
                    }


                } catch (e: Exception) {
                    Log.i("Error", e.message.toString())
                }
            }
        }
        return data
    }
}