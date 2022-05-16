package com.example.qadri.mvvm.repository

import android.util.Base64
import androidx.lifecycle.MutableLiveData
import com.example.qadri.constant.Constants
import com.example.qadri.mvvm.model.changePassword.ChangePasswordModel
import com.example.qadri.mvvm.model.changePassword.VerifyPassModel
import com.example.qadri.mvvm.model.customer.CustomerResponse
import com.example.qadri.mvvm.model.location.UserLocation
import com.example.qadri.mvvm.model.login.LoginModel
import com.example.qadri.mvvm.model.lov.LovResponse
import com.example.qadri.mvvm.model.markAttendance.MarkAttendanceModel
import com.example.qadri.mvvm.model.otp.OtpModel
import com.example.qadri.mvvm.model.portfolio.PortfolioResponse
import com.example.qadri.mvvm.network.Api
import com.example.qadri.utils.SharedPrefManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class UserRepository @Inject constructor(
    private val api: Api,
    private val sharedPrefManager: SharedPrefManager
) : BaseRepository() {

    private val sdf = SimpleDateFormat(Constants.DATE_FORMAT_2, Locale.ENGLISH)
    private val mCalender = Calendar.getInstance()

    fun login(loginModel: LoginModel): MutableLiveData<String> {
        return callApi(api.login(loginModel), Constants.LOGIN)
    }

    fun verifyOpt(otpModel: OtpModel): MutableLiveData<String> {
        return callApi(api.verifyOtp(otpModel), Constants.VERIFY_OTP)
    }

    fun verifyPasswordReq(verifyPassModel: VerifyPassModel): MutableLiveData<String> {
        return callApi(
            api.verifyPassword(
                verifyPassModel,
                "Bearer " + sharedPrefManager.getToken()
            ), Constants.VERIFY_PWD_REQ
        )
    }

    fun getUserDetails(token: String): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.getUserDetails(headerMap.apply {
                put("Authorization","Bearer " + sharedPrefManager.getToken())
                put("x-csrf-token",
                    Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                put("x-token-time",sdf.format(mCalender.time))
            }),
            Constants.USER_DETAIL
        )
    }

    fun markAttendance(
        markAttendanceModel: MarkAttendanceModel): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.markAttendance(markAttendanceModel,headerMap.apply {
                put("Authorization","Bearer " + sharedPrefManager.getToken())
                put("x-csrf-token",
                    Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                put("x-token-time",sdf.format(mCalender.time))
            }), Constants.MARK_ATTENDANCE
        )
    }

    suspend fun getLovs(): LovResponse {
        val headerMap = HashMap<String, String>()
        return api.getLovs(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        })
    }

    fun uploadUserLocation(userLocation: List<UserLocation>): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.userLocation(userLocation, headerMap.apply {
                put("Authorization","Bearer " + sharedPrefManager.getToken())
                put("x-csrf-token",
                    Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                put("x-token-time",sdf.format(mCalender.time))
            }),
            Constants.UPDATE_LOCATION
        )

    }

    fun getDashboard(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getDashboard(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.DASHBOARD_COUNT)

    }

    fun changePassword(changePasswordModel: ChangePasswordModel): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.changePassword(
                changePasswordModel,
                headerMap.apply {
                    put("Authorization","Bearer " + sharedPrefManager.getToken())
                    put("x-csrf-token",
                        Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                    put("x-token-time",sdf.format(mCalender.time))
                }
            ), Constants.CHANGE_PASSWORD
        )
    }

    suspend fun getPortfolio(): PortfolioResponse {
        val headerMap = HashMap<String, String>()
        return api.getPortfolio(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        })

    }

    fun uploadUserProfileImage(profileImage: File): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        val fileReqBody: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), profileImage)
        val part: MultipartBody.Part = MultipartBody.Part.createFormData("profile_img", profileImage.name, fileReqBody)
        return callApi(api.uploadUserProfileImage(part,headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.UPLOAD_PHOTO)
    }

    fun getCustomers(): Call<CustomerResponse> {
        val headerMap = HashMap<String, String>()
        return api.getCustomer(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        })

    }

}