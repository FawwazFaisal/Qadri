package com.example.qadri.ui.fragment.reports.bankDeposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.DialogReportBankDepositDetailBinding
import com.example.qadri.databinding.FragmentReportBankDepositBinding
import com.example.qadri.mvvm.model.reports.ReportBankDepositModel
import com.example.qadri.ui.fragment.BaseDockFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class ReportBankDeposit : BaseDockFragment(), ClickListener {

    lateinit var bd: FragmentReportBankDepositBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportBankDepositBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter = AdapterRvReportBankDeposit(this).apply {
            setList(arrayListOf<ReportBankDepositModel>().apply {
                add(ReportBankDepositModel("Usman Bukhari", "2-1-2022", "70,000"))
                add(ReportBankDepositModel("Usman Bukhari", "2-1-2022", "70,000"))
                add(ReportBankDepositModel("Usman Bukhari", "2-1-2022", "70,000"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        BottomSheetDialog(requireContext(), R.style.SheetDialog).apply {
            val bd = DialogReportBankDepositDetailBinding.inflate(layoutInflater)
            setContentView(bd.root)
        }.show()
    }
}