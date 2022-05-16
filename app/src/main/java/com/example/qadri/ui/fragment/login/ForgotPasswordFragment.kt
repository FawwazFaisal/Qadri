package com.example.qadri.ui.fragment.login

import android.os.Bundle
import android.view.*
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.qadri.R
import com.example.qadri.ui.activity.LoginActivity
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.FragmentForgotPasswordBinding
import com.example.qadri.mvvm.model.resetPassword.ResetPasswordModel
import com.example.qadri.mvvm.model.resetPassword.ResetPwdReqResponse
import com.example.qadri.utils.GsonFactory


class ForgotPasswordFragment : BaseDockFragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView()
        myDockActivity?.getUserViewModel()?.apiListener = this
        binding.btnGenOtp.setOnClickListener {
            setBackClickListener()
            onGenerateOTPCLickEvent()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setBackClickListener() {
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
    }

    private fun initView() {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
    }

    private fun onGenerateOTPCLickEvent() {
        if (binding.edForgotUserName.text.toString().isEmpty()) {
            myDockActivity?.showErrorMessage(getString(R.string.error_login_id))
            return
        }

        resetPwdReq(binding.edForgotUserName.text.toString())
    }

    private fun resetPwdReq(loginID: String) {
        myDockActivity?.showProgressIndicator()
      //  myDockActivity?.getUserViewModel()?.resetPwdReq(ResetPasswordModel(loginID))
    }

    override fun onSuccess(liveData: LiveData<String>, tag: String) {
        super.onSuccess(liveData, tag)
        myDockActivity?.hideProgressIndicator()
        when (tag) {
            Constants.RESET_PWD_REQ -> {
                try {
                    val resetReqResponseEnt = GsonFactory.getConfiguredGson()
                        ?.fromJson(liveData.value, ResetPwdReqResponse::class.java)
                    if (resetReqResponseEnt?.message != null) {
                        myDockActivity?.showSuccessMessage(resetReqResponseEnt.message.toString())
                    }
                    val bundle = Bundle()
                    bundle.putString("LOGIN_ID", binding.edForgotUserName.text.toString())
                    bundle.putBoolean("RESET_PASSWORD", true)
                    LoginActivity.navController.navigate(
                        R.id.action_forgotPasswordFragment_to_OTPVerificationFragment,
                        bundle
                    )
                } catch (e: Exception) {
                    myDockActivity?.showErrorMessage(getString(R.string.something_went_wrong))
                }
            }
        }
    }

    override fun onFailure(message: String, tag: String) {
        super.onFailure(message, tag)
        myDockActivity?.hideProgressIndicator()
    }


}