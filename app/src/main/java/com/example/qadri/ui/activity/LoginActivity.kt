package com.example.qadri.ui.activity

import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.qadri.R
import com.example.qadri.databinding.ActivityLoginBinding
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@RequiresApi(api = Build.VERSION_CODES.P)
class LoginActivity : DockActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var executor: Executor
    lateinit var bmPrompt: BiometricPrompt

    companion object {
        lateinit var navController: NavController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_login_fragment)
     //   checkForFingerPrint()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_login_fragment)
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    private fun checkForFingerPrint() {
        executor = Executors.newSingleThreadExecutor()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT) ||
            this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_FACE)
        ) {
            bmPrompt = BiometricPrompt.Builder(this)
                .setTitle("Login")
                .setDescription("scanDesc")
                .setNegativeButton("Cancel", executor,
                    { dialogInterface, i -> }).build()
        }


        if (bmPrompt != null) {
            bmPrompt.authenticate(
                CancellationSignal(),
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        runOnUiThread {

                        }
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        runOnUiThread {
                            Log.i("xxResult", result.toString())
                        }
                    }
                })
        }
    }

}