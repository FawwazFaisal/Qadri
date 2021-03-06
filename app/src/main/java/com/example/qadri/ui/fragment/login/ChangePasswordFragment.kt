package com.example.qadri.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.example.qadri.R
import com.example.qadri.ui.activity.LoginActivity
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.FragmentChangePasswordBinding
import com.example.qadri.mvvm.model.changePassword.ChangePasswordModel
import java.util.regex.Pattern

class ChangePasswordFragment : BaseDockFragment(){
    lateinit var binding: FragmentChangePasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChangePasswordBinding.inflate(layoutInflater)
        myDockActivity?.getUserViewModel()?.apiListener = this

        binding.btnSubmit.setOnClickListener {
            auth()
        }

        return binding.root
    }

    private fun auth() {
        if(binding.edOldPassword.text.toString().isNullOrEmpty()){
            binding.parentEdOldPassword.error = getString(R.string.error_empty_field)
            return
        }
        if(binding.edNewPassword.text.toString().isNullOrEmpty()){
            binding.parentEdNewPassword.error = getString(R.string.error_empty_field)
            return
        }
        if(binding.edConfirmPassword.text.toString().isNullOrEmpty()){
            binding.parentEdConfirmPassword.error = getString(R.string.error_empty_field)
            return
        }

        if((binding.edConfirmPassword.text.toString()) != (binding.edNewPassword.text.toString())){
            binding.parentEdConfirmPassword.error = getString(R.string.error_different_fields)
            return
        }

        if(!isValidPassword(binding.edNewPassword.text.toString())){
            showPasswordChangingInstructions(getString(R.string.error_wrong_password_pattern))
            return
        }
        if(binding.edNewPassword.length()<8) {
            showPasswordChangingInstructions(getString(R.string.error_wrong_password_pattern))
            return
        }

        changePassword(
            ChangePasswordModel(
                sharedPrefManager.getUsername(),
                utilHelper.encryptPass("23423532","1234567891011121",binding.edNewPassword.text.toString()).toString(),
                utilHelper.encryptPass("23423532","1234567891011121",binding.edConfirmPassword.text.toString()).toString(),
                utilHelper.encryptPass("23423532","1234567891011121",binding.edOldPassword.text.toString()).toString(),
            )
        )
    }

    private fun changePassword(changePasswordModel: ChangePasswordModel){
        myDockActivity?.getUserViewModel()?.changePassword(changePasswordModel)
    }

    override fun onSuccess(liveData: LiveData<String>, tag: String) {
        super.onSuccess(liveData, tag)
        when(tag){
            Constants.CHANGE_PASSWORD -> {
                sharedPrefManager.clear()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                utilHelper.showToast(getString(R.string.change_password_successfuly))
            }
        }
    }

    private fun isValidPassword(str: String): Boolean {
        val regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+_=;*])(?=\\S+$).{4,}$"
        val p = Pattern.compile(regex)
        return p.matcher(str).matches()
    }
}