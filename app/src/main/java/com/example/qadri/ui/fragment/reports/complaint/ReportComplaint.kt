package com.example.qadri.ui.fragment.reports.complaint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.DialogRecoveryDetailsBinding
import com.example.qadri.databinding.DialogReportComplaintBinding
import com.example.qadri.databinding.FragmentReportComplaintBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class ReportComplaint : BaseDockFragment(), ClickListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bd.recyclerView.adapter = AdapterRvComplaint(this).apply {
//            setList(arrayListOf<ReportComplaintModel>().apply {
//                add(ReportComplaintModel("Ahsan Mehboob","03001234567","A-Category","2-2-2022"))
//                add(ReportComplaintModel("Ahsan Mehboob","03001234567","A-Category","2-2-2022"))
//                add(ReportComplaintModel("Ahsan Mehboob","03001234567","A-Category","2-2-2022"))
//            })
//        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        BottomSheetDialog(requireContext(),R.style.SheetDialog).apply {
            val bd = DialogReportComplaintBinding.inflate(layoutInflater)
            setContentView(bd.root)
        }.show()
    }


}