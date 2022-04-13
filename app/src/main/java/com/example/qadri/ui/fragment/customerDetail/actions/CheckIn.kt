package com.example.qadri.ui.fragment.customerDetail.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentCheckInBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class CheckIn : BaseDockFragment() {

    lateinit var bd : FragmentCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentCheckInBinding.inflate(layoutInflater)
        return bd.root
    }
}