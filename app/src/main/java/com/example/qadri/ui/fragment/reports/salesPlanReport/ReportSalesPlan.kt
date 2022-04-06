package com.example.qadri.ui.fragment.reports.salesPlanReport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentSalesPlanVisitReportBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class ReportSalesPlan : BaseDockFragment() {

    lateinit var bd : FragmentSalesPlanVisitReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentSalesPlanVisitReportBinding.inflate(layoutInflater)
        return bd.root
    }
}