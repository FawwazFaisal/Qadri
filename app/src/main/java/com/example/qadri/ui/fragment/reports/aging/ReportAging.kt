package com.example.qadri.ui.fragment.reports.aging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.DialogReportAgingDetailBinding
import com.example.qadri.databinding.FragmentReportAgingBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class ReportAging : BaseDockFragment(), ClickListener {

    lateinit var bd: FragmentReportAgingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportAgingBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bd.recyclerView.adapter = AdapterRvAging(this).apply {
//            setList(arrayListOf<ReportAgingModel>().apply {
//                add(ReportAgingModel("Al Yaqeen Arts Printer", "175,000", "200,000"))
//                add(ReportAgingModel("Al Yaqeen Arts Printer", "175,000", "200,000"))
//                add(ReportAgingModel("Al Yaqeen Arts Printer", "175,000", "200,000"))
//            })
//        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        BottomSheetDialog(requireContext(), R.style.SheetDialog).apply {
            val bd = DialogReportAgingDetailBinding.inflate(layoutInflater)
            setContentView(bd.root)
        }.show()
    }
}