package com.example.qadri.mvvm.viewModel

import androidx.lifecycle.ViewModel
import com.example.qadri.mvvm.model.branchDirectory.SubmitReport
import com.example.qadri.mvvm.model.changePassword.ChangePasswordModel
import com.example.qadri.mvvm.model.changePassword.VerifyPassModel
import com.example.qadri.mvvm.model.cutomerTagging.CustomerTaggingModel
import com.example.qadri.mvvm.model.login.LoginModel
import com.example.qadri.mvvm.model.markAttendance.MarkAttendanceModel
import com.example.qadri.mvvm.model.otp.OtpModel
import com.example.qadri.mvvm.model.resetPassword.ResetPasswordModel
import com.example.qadri.mvvm.model.trainingAndQuiz.GetQuizModel
import com.example.qadri.mvvm.model.trainingAndQuiz.QuizResultModel
import com.example.qadri.mvvm.model.trainingAndQuiz.SubmitQuizModel
import com.example.qadri.mvvm.network.ApiListener
import com.example.qadri.mvvm.repository.UserRepository
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    var apiListener: ApiListener? = null

    fun login(loginModel: LoginModel){
        userRepository.apiListener = apiListener
        userRepository.login(loginModel)
    }

    fun verifyOtp(otpModel: OtpModel){
        userRepository.apiListener = apiListener
        userRepository.verifyOpt(otpModel)
    }

    fun resetPwdReq(resetPasswordModel: ResetPasswordModel){
        userRepository.apiListener = apiListener
        userRepository.resetPasswordReq(resetPasswordModel)
    }

    fun verifyPwdReq(verifyPassModel: VerifyPassModel){
        userRepository.apiListener = apiListener
        userRepository.verifyPasswordReq(verifyPassModel)
    }

    fun uerDetails(token: String){
        userRepository.apiListener = apiListener
        userRepository.getUserDetails(token)
    }


    fun markAttendance(markAttendanceModel: MarkAttendanceModel){
        userRepository.apiListener = apiListener
        userRepository.markAttendance(markAttendanceModel)
    }
    fun getDynamicLeads(token: String){
        userRepository.apiListener = apiListener
        userRepository.getDynamicLeads(token)
    }

    fun getDashBoard() {
        userRepository.apiListener = apiListener
        userRepository.getDashboard()
    }

    fun changePassword(changePasswordModel: ChangePasswordModel) {
        userRepository.apiListener = apiListener
        userRepository.changePassword(changePasswordModel)
    }

    fun getTrainings() {
        userRepository.apiListener = apiListener
        userRepository.getTrainings()
    }

    fun getTrainingResult(quizResultModel: QuizResultModel) {
        userRepository.apiListener = apiListener
        userRepository.getTrainingResult(quizResultModel)
    }

    fun getQuiz(getQuizModel: GetQuizModel) {
        userRepository.apiListener = apiListener
        userRepository.getQuizes(getQuizModel)
    }

    fun submitQuiz(submitQuizModel: SubmitQuizModel) {
        userRepository.apiListener = apiListener
        userRepository.submitQuiz(submitQuizModel)
    }
    fun jointVisit(jointVisit : JSONObject) {
        userRepository.apiListener = apiListener
        userRepository.jointVisit(jointVisit)
    }
    fun submitBranchVisitReport(submitReport: SubmitReport) {
        userRepository.apiListener = apiListener
        userRepository.submitBranchVisitReport(submitReport)
    }

    fun getNotification() {
        userRepository.apiListener = apiListener
        userRepository.getNotification()
    }

    fun getNotificationResult(notification_id: String) {
        userRepository.apiListener = apiListener
        userRepository.getNotificationResult(notification_id)
    }

    fun getHallOfFame() {
        userRepository.apiListener = apiListener
        userRepository.getHallOfFame()
    }

    fun getCustomerTagging(customerTaggingModel: CustomerTaggingModel) {
        userRepository.apiListener = apiListener
        userRepository.getCustomerTagging(customerTaggingModel)
    }

    suspend fun getPortfolio() {
        userRepository.apiListener = apiListener
        userRepository.getPortfolio()
    }

    fun getBirthdayNotification() {
        userRepository.apiListener = apiListener
        userRepository.getBirthdayNotification()
    }

    fun uploadUserProfileImage(mediaFile: File) {
        userRepository.apiListener = apiListener
        userRepository.uploadUserProfileImage(mediaFile)
    }

    fun getDepositData() {
        userRepository.apiListener = apiListener
        userRepository.getDepositData()
    }

    fun getMovement() {
        userRepository.apiListener = apiListener
        userRepository.getMovement()
    }

    fun getTop150() {
        userRepository.apiListener = apiListener
        userRepository.getTop150()
    }

    fun getTierWise() {
        userRepository.apiListener = apiListener
        userRepository.getTierWise()
    }

    fun getWealthList() {
        userRepository.apiListener = apiListener
        userRepository.getWealthList()
    }

    fun getCrossSellReport() {
        userRepository.apiListener = apiListener
        userRepository.getCrossSellReport()
    }

    fun getAdcList() {
        userRepository.apiListener = apiListener
        userRepository.getAdcList()
    }

    fun getControlsReport() {
        userRepository.apiListener = apiListener
        userRepository.getControlsReport()
    }

    fun getKafalahReport() {
        userRepository.apiListener = apiListener
        userRepository.getKafalahReport()
    }

}