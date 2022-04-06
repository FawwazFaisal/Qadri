package com.example.qadri.ui.fragment.reports.aging

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentReportAgaingBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class ReportAging : BaseDockFragment() {

    lateinit var bd: FragmentReportAgaingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportAgaingBinding.inflate(layoutInflater)
        return bd.root
    }
}