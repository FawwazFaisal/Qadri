package com.example.qadri.ui.fragment.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentAddCustomerBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class AddCustomer : BaseDockFragment() {

    lateinit var bd : FragmentAddCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentAddCustomerBinding.inflate(layoutInflater)
        return bd.root
    }
}