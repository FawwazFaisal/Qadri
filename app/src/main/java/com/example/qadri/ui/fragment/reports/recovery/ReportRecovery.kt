package com.example.qadri.ui.fragment.reports.recovery

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.DialogFilterReportsBinding
import com.example.qadri.databinding.DialogRecoveryDetailsBinding
import com.example.qadri.databinding.FragmentReportRecoveryBinding
import com.example.qadri.mvvm.model.reports.RecoveryReportModel
import com.example.qadri.ui.fragment.BaseDockFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class ReportRecovery : BaseDockFragment(), ClickListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.titleLayout.search.setOnClickListener {
            openSearchDialog()
        }

        bd.recyclerView.adapter = AdapterRvRecovery(this).apply {
            setList(arrayListOf<RecoveryReportModel>().apply {
                add(RecoveryReportModel("Adnan Mahboob","03002001100","A-Category","Amount: 150,000"))
                add(RecoveryReportModel("Adnan Mahboob","03002001100","A-Category","Amount: 150,000"))
                add(RecoveryReportModel("Adnan Mahboob","03002001100","A-Category","Amount: 150,000"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        val reportItem = data as RecoveryReportModel
        BottomSheetDialog(requireContext(),R.style.SheetDialog).apply {
            val bd = DialogRecoveryDetailsBinding.inflate(layoutInflater)
            setContentView(bd.root)
        }.show()

    }
}