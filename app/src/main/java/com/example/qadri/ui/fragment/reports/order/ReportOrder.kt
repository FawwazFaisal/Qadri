package com.example.qadri.ui.fragment.reports.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentReportOrderBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class ReportOrder : BaseDockFragment() {

    lateinit var bd : FragmentReportOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportOrderBinding.inflate(layoutInflater)
        return bd.root
    }
}