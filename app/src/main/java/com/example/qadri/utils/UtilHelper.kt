package com.example.qadri.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Base64
import android.widget.Toast
import javax.crypto.SecretKey
import javax.inject.Inject

class UtilHelper @Inject constructor(private val context: Context) {

    var key: SecretKey? = null

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String? {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    fun encodeToBase64(password:String): String {
        val key = Base64.encodeToString(password.toByteArray(), Base64.DEFAULT)
        return key
    }
}