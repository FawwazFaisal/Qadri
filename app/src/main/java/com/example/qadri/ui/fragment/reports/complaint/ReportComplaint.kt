package com.example.qadri.ui.fragment.reports.complaint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentReportComplaintBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class ReportComplaint : BaseDockFragment() {

    lateinit var bd:FragmentReportComplaintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportComplaintBinding.inflate(layoutInflater)
        return bd.root
    }
}