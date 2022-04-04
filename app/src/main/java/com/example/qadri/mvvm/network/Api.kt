package com.example.qadri.mvvm.network

import com.example.qadri.mvvm.model.addLead.CustomerDetail
import com.example.qadri.mvvm.model.addLead.DynamicLeadsItem
import com.example.qadri.mvvm.model.branchDirectory.SubmitReport
import com.example.qadri.mvvm.model.changePassword.ChangePasswordModel
import com.example.qadri.mvvm.model.changePassword.VerifyPassModel
import com.example.qadri.mvvm.model.checkin.CheckinModel
import com.example.qadri.mvvm.model.cutomerTagging.CustomerTaggingModel
import com.example.qadri.mvvm.model.generic.GenericMsgResponse
import com.example.qadri.mvvm.model.location.UserLocation
import com.example.qadri.mvvm.model.login.LoginModel
import com.example.qadri.mvvm.model.lov.LovResponse
import com.example.qadri.mvvm.model.markAttendance.MarkAttendanceModel
import com.example.qadri.mvvm.model.otp.OtpModel
import com.example.qadri.mvvm.model.portfolio.PortfolioResponse
import com.example.qadri.mvvm.model.resetPassword.ResetPasswordModel
import com.example.qadri.mvvm.model.trainingAndQuiz.GetQuizModel
import com.example.qadri.mvvm.model.trainingAndQuiz.QuizResultModel
import com.example.qadri.mvvm.model.trainingAndQuiz.SubmitQuizModel
import com.example.qadri.mvvm.model.visitLogs.VisitsCallModel
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
    @GET("leads/getLovs")
    suspend fun getLovs(
        @HeaderMap headers: Map<String, String>
    ): LovResponse

    //Get Dynamic Data
    @GET("leads/getLeadsForDynamicData")
    fun getLeadsForDynamicData(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Leads
    @GET("leads/getLeads")
    suspend fun getLeads(
        @HeaderMap headers: Map<String, String>
    ): ArrayList<DynamicLeadsItem>

    //Add Leads
    @POST("leads/addLead")
    fun addLead(
        @Body customerDetail: CustomerDetail,
        @HeaderMap headers: Map<String, String>
    ): Call<DynamicLeadsItem>


    //Add Lead Checkin
    @POST("leads/addLeadCheckin")
    fun addLeadCheckin(
        @Body checkinModel: CheckinModel,
        @HeaderMap headers: Map<String, String>
    ): Call<GenericMsgResponse>

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

    //Get Marketing Collateral
    @GET("marketingcollateral")
    fun getmarketingcollateral(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Upload UserTracking
    @POST("auth/userTracking")
    fun userLocation(
        @Body customerDetail: List<UserLocation>,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Visits Calls
    @POST("leads/getVisitsCalls")
    fun getVisitsCalls(
        @Body visitsCallModel: VisitsCallModel,
        @HeaderMap headers: Map<String, String>
    ): Call<ArrayList<CheckinModel>>

    //Get Trainings
    @POST("training/getTrainings")
    fun getTrainings(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Trainings
    @POST("training/getQuizes")
    fun getQuizes(
        @Body getQuizModel: GetQuizModel,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Trainings
    @POST("training/submitQuiz")
    fun submitQuiz(
        @Body submitQuizModel: SubmitQuizModel,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Trainings
    @POST("leads/jointVisit")
    fun jointVisit(
        @Body jointVisit: JSONObject,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //submit Branch Visit Report
    @POST("sfts/branchVisitForm")
    fun submitBranchVisitReport(
        @Body submitReport: SubmitReport,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Notification
    @POST("notification")
    fun getNotification(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Notification
    @POST("notification/markAsRead")
    fun getNotificationResult(
        @Body notif_id: String,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Branch Directory
    @POST("sfts/getSftsBranchDirectories")
    fun getBranchDirectory(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Branch Directory
    @POST("notification/getCustomTemplate")
    fun getSmsTemplate(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Hall of fame
    @POST("hallOfFame")
    fun getHallOfFame(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Customer Tagging
    @POST("getCustomerTagging")
    fun getCustomerTagging(
        @Body customerTaggingModel: CustomerTaggingModel,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Portfolio
    @POST("portfolio/sfts")
    suspend fun getPortfolio(
        @HeaderMap headers: Map<String, String>
    ): PortfolioResponse

    // Trainings Result
    @POST("training/quizResult")
    fun getTrainingResult(
        @Body quizResultModel: QuizResultModel,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //User Profile Image
    @Multipart
    @POST("auth/userProfileImg")
    fun uploadUserProfileImage(
        @Part profile_img: MultipartBody.Part,
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Birthday Notification
    @POST("notification/getBirthdayNotification")
    fun getBirthdayNotification(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Deposit Report
    @POST("report/GetDepositData")
    fun getDepositData(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetMovementData")
    fun getMovement(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetTop150Data")
    fun getTop150(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetTierWiseData")
    fun getTierWise(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetNTBData")
    fun getNtbList(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetWealthData")
    fun getWealthList(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetCrossSellData")
    fun getCrossSellReport(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetADCData")
    fun getAdcList(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetControlsData")
    fun getControlsReport(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

    //Get Movement Report
    @POST("report/GetKafalahData")
    fun getKafalahReport(
        @HeaderMap headers: Map<String, String>
    ): Call<ResponseBody>

}