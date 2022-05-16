package com.example.qadri.mvvm.network

import com.example.qadri.mvvm.model.changePassword.ChangePasswordModel
import com.example.qadri.mvvm.model.changePassword.VerifyPassModel
import com.example.qadri.mvvm.model.customer.CustomerResponse
import com.example.qadri.mvvm.model.location.UserLocation
import com.example.qadri.mvvm.model.login.LoginModel
import com.example.qadri.mvvm.model.lov.LovResponse
import com.example.qadri.mvvm.model.markAttendance.MarkAttendanceModel
import com.example.qadri.mvvm.model.otp.OtpModel
import com.example.qadri.mvvm.model.portfolio.PortfolioResponse
import com.example.qadri.mvvm.model.resetPassword.ResetPasswordModel
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface Api {

    //Login
    @POST("login")
    fun login(
        @Body loginModel: LoginModel
    ): Call<ResponseBody>

    //OTP
    @POST("verifyOtp")
    fun verifyOtp(
        @Body otpModel: OtpModel
    ): Call<ResponseBody>

    //Reset Password Request
    @POST("resetPasswordRequest")
    fun resetPasswordReq(
        @Body resetPasswordModel: ResetPasswordModel
    ): Call<ResponseBody>

    //Verify Password Request
    @POST("auth/resetPasswordVerify")
    fun verifyPassword(
        @Body verifyPassModel: VerifyPassModel, @Header("Authorization") token: String
    ): Call<ResponseBody>

    //Get UserDetails
    @GET("auth/getUserDetails")
    fun getUserDetails(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Verify Password Request
    @POST("auth/markAttendance")
    fun markAttendance(
        @Body markAttendanceModel: MarkAttendanceModel,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Lovs
    @GET("leads/getLovspjp")
    suspend fun getLovs(
        @HeaderMap headers: Map<String, String>
    ): LovResponse

    //Dashboard Count
    @POST("dashboard/sfts")
    fun getDashboard(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Change Password
    @POST("auth/changePassword")
    fun changePassword(
        @Body changePasswordModel: ChangePasswordModel,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Upload UserTracking
    @POST("auth/userTracking")
    fun userLocation(
        @Body customerDetail: List<UserLocation>,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Portfolio
    @POST("portfolio/sfts")
    suspend fun getPortfolio(
        @HeaderMap headers: Map<String, String>
    ): PortfolioResponse

    //User Profile Image
    @Multipart
    @POST("auth/userProfileImg")
    fun uploadUserProfileImage(
        @Part profile_img: MultipartBody.Part,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Change Password
    @POST("portfolio/getCustomerPjp")
    fun getCustomer(
        @HeaderMap headers: Map<String, String>
    ): Call<CustomerResponse>


}