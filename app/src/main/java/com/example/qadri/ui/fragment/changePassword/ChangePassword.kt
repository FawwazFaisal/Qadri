package com.example.qadri.ui.fragment.changePassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentChangePassword2Binding
import com.example.qadri.databinding.FragmentChangePasswordBinding
import com.example.qadri.ui.fragment.BaseDockFragment


class ChangePassword : BaseDockFragment() {

    lateinit var bd : FragmentChangePassword2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd  = FragmentChangePassword2Binding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}