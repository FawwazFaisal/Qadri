package com.example.qadri.ui.activity

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.qadri.R
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.ActivitySplashBinding
import com.example.qadri.security.EncryptionKeyStoreImpl
import com.example.qadri.utils.SharedPrefKeyManager
import com.example.qadri.utils.SharedPrefManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : DockActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val TIME_OUT: Long = 2500
    val encryptionKeyStore = EncryptionKeyStoreImpl.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SharedPrefKeyManager.with(this)
        val isFirstRun = sharedPrefManager.get(Constants.FIRST_TIME) as Boolean?
        encryptionKeyStore.setContext(this)

        if (isFirstRun == null) {
            sharedPrefManager.put(false, Constants.FIRST_TIME)
            encryptionKeyStore.generateKey()
        } else
            encryptionKeyStore.loadKey()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Dexter.withContext(this)
                .withPermissions(
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.USE_BIOMETRIC,
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                        logoAnimation()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<PermissionRequest>,
                        token: PermissionToken
                    ) { /* ... */
                    }
                }).check()
        }

    }

    private fun logoAnimation() {
        Handler(Looper.getMainLooper()).postDelayed({
            nextView()
        }, TIME_OUT)
    }


    private fun nextView() {

        if (sharedPrefManager.getToken().isNotEmpty()) {
            if (sharedPrefManager.getShiftStart()){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}