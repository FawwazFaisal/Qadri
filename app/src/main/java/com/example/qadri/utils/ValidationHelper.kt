package com.example.qadri.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.qadri.R
import com.example.qadri.ui.activity.ChangePasswordActivity
import com.example.qadri.ui.activity.LoginActivity
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class ValidationHelper @Inject constructor(private val context: Context) {

    fun validateString(editText: EditText): Boolean {
        val text = editText.text.toString()
        if (text.isEmpty()) {
            editText.error = context.getString(R.string.this_field_is_required)
            return false;
        } else {
            editText.error = null
            return true;
        }
    }

    fun validateEmailFormat(editText: EditText): Boolean {
        val email = editText.text.toString()
        val pattern: Pattern;
        val matcher: Matcher;
        val EMAIL_PATTERN = ".+@.+\\.[a-z]+";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email)
        if (!matcher.matches()) {
            editText.error = context.getString(R.string.please_enter_valid_email_address)
            return false
        }
        return true
    }

    fun validateConfirmPassword(input1: EditText, input2: EditText): Boolean {
        val inputTxt1 = input1.text.toString()
        val inpuTxtt2 = input2.text.toString()
        if (inputTxt1 != inpuTxtt2) {
            input2.error = "Password doesn't match"
            return false
        }
        return true
    }


    fun validatePhone(editText: EditText): Boolean {
        val text = editText.text.toString()
        if (text.isEmpty()) {
            editText.error = context.getString(R.string.this_field_is_required)
            return false
        } else if (text.length != 11) {
            editText.error = context.getString(R.string.please_enter_valid_phone_number)
            return false
        } else {
            editText.error = null
            return true
        }
    }

    fun validateAccount(editText: EditText): Boolean {
        val text = editText.text.toString()
        if (text.isEmpty()) {
            editText.error = context.getString(R.string.this_field_is_required)
            return false
        } else if (text.length != 11) {
            editText.error = context.getString(R.string.please_enter_valid_account_number)
            return false
        } else {
            editText.error = null
            return true
        }
    }

    fun validateYear(editText: EditText): Boolean {
        val text = editText.text.toString()
        if (text.isEmpty()) {
            editText.error = context.getString(R.string.this_field_is_required)
            return false
        } else if (text.length != 4) {
            editText.error = context.getString(R.string.please_enter_valid_year)
            return false
        } else {
            editText.error = null
            return true
        }
    }

//    fun validCnic(evCnic: EditText): Boolean {
//        var evCnicTxt = evCnic.text.toString()
//        evCnicTxt = evCnicTxt.replace("-", "")
//        if (evCnicTxt.length == 13) {
//            evCnic.error = null
//            return true
//        } else {
//            evCnic.error = context.getString(R.string.please_enter_valid_cnic_number)
//            return false
//        }
//    }

//    fun validCnic(expiryDate: MaskEditText): Boolean {
//        if (expiryDate.rawText?.length == 13) {
//            expiryDate.error = null
//            return true
//        } else {
//            expiryDate.error = context.getString(R.string.please_enter_valid_cnic_number)
//            return false
//        }
//    }

    fun navigateToLogin() {
        val loginIntent = Intent(context, LoginActivity::class.java)
        //loginIntent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(loginIntent)
    }

    fun navigateToChangePassword() {
        val changePassIntent = Intent(context, ChangePasswordActivity::class.java)
        context.startActivity(changePassIntent)
    }

    fun validateSpinner(spinner: Spinner, error: String?): Boolean {
        val selectedView: View = spinner.getSelectedView()
        if (selectedView != null && selectedView is TextView) {
            val selectedTextView: TextView = selectedView as TextView
            if (selectedTextView.getText().equals("")) {
                selectedTextView.setError(error)
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                return false
            }
        }
        return true
    }


}