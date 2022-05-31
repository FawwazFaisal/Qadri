package com.example.qadri.ui.activity

import android.annotation.SuppressLint
import android.content.*
import android.location.Location
import android.os.Bundle
import android.util.Log
import com.example.qadri.R
import com.example.qadri.databinding.ActivityWelcomeBinding
import com.example.qadri.ui.fragment.login.WelcomeFragment
import com.example.qadri.location.ForegroundOnlyLocationService
import com.example.qadri.location.toText
import com.example.qadri.utils.SharedPrefKeyManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : DockActivity() {
    lateinit var binding: ActivityWelcomeBinding

    companion object {
         var foregroundOnlyLocationService: ForegroundOnlyLocationService? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SharedPrefKeyManager.with(this)
        initFragment()
    }

    private fun initFragment() {
        replaceDockableFragmentWithoutBackStack(WelcomeFragment())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun logResultsToScreen(output: String) {
        Log.i("Foreground", output)
    }

    /**
     * Receiver for location broadcasts from [ForegroundOnlyLocationService].
     */
    private inner class ForegroundOnlyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val location = intent.getParcelableExtra<Location>(
                ForegroundOnlyLocationService.EXTRA_LOCATION
            )

            if (location != null) {
                logResultsToScreen("Foreground location: ${location.toText()}")
            }
        }
    }
}