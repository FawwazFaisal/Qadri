package com.example.qadri.ui.fragment.reports.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.FragmentReportOrderBinding
import com.example.qadri.mvvm.model.reports.RecoveryReportModel
import com.example.qadri.ui.fragment.BaseDockFragment

class ReportOrder : BaseDockFragment(), ClickListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter = AdapterRvOrder(this).apply {
            setList(arrayListOf<RecoveryReportModel>().apply {
                add(RecoveryReportModel("Adnan Mahboob","03002001100","A-Category","Amount: 150,000"))
                add(RecoveryReportModel("Adnan Mahboob","03002001100","A-Category","Amount: 150,000"))
                add(RecoveryReportModel("Adnan Mahboob","03002001100","A-Category","Amount: 150,000"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        val order = data as RecoveryReportModel
        navigateToFragment(R.id.action_reportOrder_to_visitOrderDetail,Bundle().apply {
            putSerializable("data",order)
        })
    }
}