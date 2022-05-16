package com.example.qadri.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.qadri.R
import com.example.qadri.ui.activity.LoginActivity
import com.example.qadri.ui.activity.WelcomeActivity
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.FragmentLoginBinding
import com.example.qadri.mvvm.model.login.LoginModel
import com.example.qadri.mvvm.model.login.LoginResponse
import com.example.qadri.utils.GsonFactory

class LoginFragment : BaseDockFragment() {

    private lateinit var binding: FragmentLoginBinding

    lateinit var email: String
    lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initView()
        binding.txtForgotPassword.setOnClickListener(::onCLickEvent)
        binding.btnLogin.setOnClickListener(::onCLickEvent)
        myDockActivity?.getUserViewModel()?.apiListener = this

        return binding.root
    }

    private fun initView() {
        binding = FragmentLoginBinding.inflate(layoutInflater)
    }

    private fun onCLickEvent(view: View) {
        when (view.id) {
            R.id.txtForgotPassword -> findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment)
            R.id.btnLogin -> auth()
        }
    }

    private fun auth() {
        when {
            isEmpty(binding.edUserName.text.toString()) -> {
                myDockActivity?.showErrorMessage(getString(R.string.error_empty_email))
            }
            isEmpty(binding.edPassword.text.toString()) -> {
                myDockActivity?.showErrorMessage(getString(R.string.error_empty_pass))
            }
            else -> {
                loginUser()
            }
        }
    }

    private fun loginUser() {
        email = binding.edUserName.text.toString()
        password = binding.edPassword.text.toString()
        myDockActivity?.showProgressIndicator()
        myDockActivity?.getUserViewModel()?.login(LoginModel(email,  utilHelper.encryptPass("23423532","1234567891011121",password).toString(),
            utilHelper.getDeviceId(requireContext()).toString()))
    }

    override fun onSuccess(liveData: LiveData<String>, tag: String) {
        super.onSuccess(liveData, tag)
        myDockActivity?.hideProgressIndicator()
        when (tag) {
            Constants.LOGIN -> {
                try {
                    val loginResponseEnt = GsonFactory.getConfiguredGson()?.fromJson(liveData.value, LoginResponse::class.java)
                    if (loginResponseEnt?.two_factor == "yes") {
                        val bundle = Bundle()
                        bundle.putString("LOGIN_ID", binding.edUserName.text.toString())
                        sharedPrefManager.setUsername(binding.edUserName.text.toString())
                        findNavController().navigate(R.id.action_loginFragment_to_otpFragment, bundle)

                    } else {
                        sharedPrefManager.setToken(loginResponseEnt?.token.toString())
                        startActivity(Intent(requireContext(), WelcomeActivity::class.java))
                        activity?.finish()
                    }
                } catch (e: Exception) {
                    myDockActivity?.showErrorMessage(getString(R.string.something_went_wrong))
                }
            }
        }
    }

    override fun onFailure(message: String, tag: String) {
        super.onFailure(message, tag)
        myDockActivity?.hideProgressIndicator()
        if (tag == Constants.LOGIN) {
            Log.i("xxError", "Error")
        }
    }

}