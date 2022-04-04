package com.example.qadri.mvvm.repository

import android.util.Base64
import androidx.lifecycle.MutableLiveData
import com.example.qadri.constant.Constants
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
import com.example.qadri.mvvm.network.Api
import com.example.qadri.utils.SharedPrefManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
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

    fun resetPasswordReq(resetPasswordModel: ResetPasswordModel): MutableLiveData<String> {
        return callApi(api.resetPasswordReq(resetPasswordModel), Constants.RESET_PWD_REQ)
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

    fun getDynamicLeads(token: String): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.getLeadsForDynamicData(headerMap.apply {
                put("Authorization","Bearer " + sharedPrefManager.getToken())
                put("x-csrf-token",
                    Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                put("x-token-time",sdf.format(mCalender.time))
            }),
            Constants.GET_DYNAMIC_LEADS
        )
    }

    suspend fun getLeads(): ArrayList<DynamicLeadsItem> {
        val headerMap = HashMap<String, String>()
        return api.getLeads(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        })
    }

    fun getTrainings(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getTrainings(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }),Constants.NOTIFICATION)
    }

    fun getQuizes(getQuizModel: GetQuizModel): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getQuizes(getQuizModel,headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }),Constants.MATERIAL)
    }

    fun submitQuiz(submitQuizModel: SubmitQuizModel): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.submitQuiz(submitQuizModel,headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }),Constants.SUBMIT_QUIZ)
    }

    fun jointVisit(jointVisit: JSONObject): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.jointVisit(jointVisit,headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }),Constants.JOINT_VISIT_API)
    }
    fun submitBranchVisitReport(submitReport: SubmitReport): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.submitBranchVisitReport(submitReport,headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }),Constants.SUBMIT_REPORT)
    }

    fun getVisitCalls(visitsCallModel: VisitsCallModel): Call<ArrayList<CheckinModel>> {
        val headerMap = HashMap<String, String>()
        return api.getVisitsCalls(visitsCallModel,headerMap.apply {
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

    fun addLead(customerDetail: CustomerDetail): Call<DynamicLeadsItem> {
        val headerMap = HashMap<String, String>()
        return api.addLead(customerDetail, headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        })
    }

    fun addLeadCheckin(checkinModel: CheckinModel): Call<GenericMsgResponse> {
        val headerMap = HashMap<String, String>()
        return api.addLeadCheckin(checkinModel, headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        })
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

    fun getNotification(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.getNotification(headerMap.apply {
                put("Authorization","Bearer " + sharedPrefManager.getToken())
                put("x-csrf-token",
                    Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                put("x-token-time",sdf.format(mCalender.time))
            }),
            Constants.NOTIFICATIONS
        )
    }

    fun getNotificationResult(notif_id: String): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.getNotificationResult(notif_id,headerMap.apply {
                put("Authorization","Bearer " + sharedPrefManager.getToken())
                put("x-csrf-token",
                    Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                put("x-token-time",sdf.format(mCalender.time))
            }),
            Constants.NOTIFICATIONS_RESULT
        )
    }

    fun getHallOfFame(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.getHallOfFame(headerMap.apply {
                put("Authorization","Bearer " + sharedPrefManager.getToken())
                put("x-csrf-token",
                    Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                put("x-token-time",sdf.format(mCalender.time))
            }),
            Constants.HALL_OF_FAME
        )
    }

    fun getCustomerTagging(customerTaggingModel: CustomerTaggingModel): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(
            api.getCustomerTagging(customerTaggingModel,headerMap.apply {
                put("Authorization","Bearer " + sharedPrefManager.getToken())
                put("x-csrf-token",
                    Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
                put("x-token-time",sdf.format(mCalender.time))
            }),
            Constants.CUSTOMER_TAGGING
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

    fun getBirthdayNotification(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getBirthdayNotification(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.BIRTHDAY_NOTIFICATION)
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

    fun getTrainingResult(quizResultModel: QuizResultModel): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getTrainingResult(quizResultModel,headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }),Constants.TRAINING_RESULT)
    }

    fun getDepositData(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getDepositData(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.DEPOSIT_REPORTS)
    }

    fun getMovement(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getMovement(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.MOVEMENT_REPORTS)
    }

    fun getTop150(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getTop150(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.TOP150CUSTOMERS)
    }

    fun getTierWise(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getTierWise(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.TIERWISEDEPOSIT)
    }

    fun getWealthList(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getWealthList(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.WEALTH)
    }

    fun getCrossSellReport(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getCrossSellReport(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.CROSS_SELL)
    }

    fun getAdcList(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getAdcList(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.ADC)
    }

    fun getControlsReport(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getControlsReport(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.CONTROL_REPORT)
    }

    fun getKafalahReport(): MutableLiveData<String> {
        val headerMap = HashMap<String, String>()
        return callApi(api.getKafalahReport(headerMap.apply {
            put("Authorization","Bearer " + sharedPrefManager.getToken())
            put("x-csrf-token",
                Base64.encodeToString("${sharedPrefManager.getToken()}${sdf.format(mCalender.time)}".toByteArray(), Base64.NO_WRAP))
            put("x-token-time",sdf.format(mCalender.time))
        }), Constants.KAFALAH_REPORT)
    }
}