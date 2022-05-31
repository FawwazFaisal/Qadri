package com.example.qadri.mvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.qadri.mvvm.model.changePassword.ChangePasswordModel
import com.example.qadri.mvvm.model.changePassword.VerifyPassModel
import com.example.qadri.mvvm.model.customer.CustomerResponse
import com.example.qadri.mvvm.model.login.LoginModel
import com.example.qadri.mvvm.model.markAttendance.MarkAttendanceModel
import com.example.qadri.mvvm.model.otp.OtpModel
import com.example.qadri.mvvm.network.ApiListener
import com.example.qadri.mvvm.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor () : ViewModel() {

    @Inject
    lateinit var userRepository: UserRepository

    var apiListener: ApiListener? = null

    fun login(loginModel: LoginModel){
        userRepository.apiListener = apiListener
        userRepository.login(loginModel)
    }

    fun verifyOtp(otpModel: OtpModel){
        userRepository.apiListener = apiListener
        userRepository.verifyOpt(otpModel)
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

    fun getDashBoard() {
        userRepository.apiListener = apiListener
        userRepository.getDashboard()
    }

    fun changePassword(changePasswordModel: ChangePasswordModel) {
        userRepository.apiListener = apiListener
        userRepository.changePassword(changePasswordModel)
    }

    fun uploadUserProfileImage(mediaFile: File) {
        userRepository.apiListener = apiListener
        userRepository.uploadUserProfileImage(mediaFile)
    }


}