package com.example.qadri.ui.fragment.reports.recovery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentReportRecoveryBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class ReportRecovery : BaseDockFragment() {

    lateinit var bd : FragmentReportRecoveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportRecoveryBinding.inflate(layoutInflater)
        return bd.root
    }
}