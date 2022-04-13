package com.example.qadri.ui.fragment.customerDetail.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentAccountsReceivablesBinding
import com.example.qadri.databinding.FragmentPreviousVisitBinding
import com.example.qadri.databinding.FragmentUpdateLocationBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class UpdateLocation : BaseDockFragment() {

    lateinit var bd: FragmentUpdateLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentUpdateLocationBinding.inflate(layoutInflater)
        return bd.root
    }
}