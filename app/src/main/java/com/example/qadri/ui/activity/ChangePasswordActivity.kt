package com.example.qadri.ui.activity

import android.os.Bundle
import com.example.qadri.R
import com.example.qadri.databinding.ActivityChangePasswordBinding
import com.example.qadri.ui.fragment.login.ChangePasswordFragment

class ChangePasswordActivity : DockActivity() {
    lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
    }

    private fun initFragment() {
        replaceDockableFragmentWithoutBackStack(ChangePasswordFragment())
    }
}