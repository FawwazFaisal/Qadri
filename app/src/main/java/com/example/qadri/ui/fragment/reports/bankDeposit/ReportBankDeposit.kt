package com.example.qadri.ui.fragment.reports.bankDeposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentReportBankDepositBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class ReportBankDeposit : BaseDockFragment() {

    lateinit var bd : FragmentReportBankDepositBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportBankDepositBinding.inflate(layoutInflater)
        return bd.root
    }
}