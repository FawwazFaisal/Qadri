package com.example.qadri.ui.fragment.reports.visit

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.DialogFilterReportsBinding
import com.example.qadri.databinding.FragmentReportRecoveryBinding
import com.example.qadri.databinding.FragmentReportVisitBinding
import com.example.qadri.ui.fragment.BaseDockFragment


class ReportVisit : BaseDockFragment(), ClickListener {

    lateinit var bd: FragmentReportVisitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportVisitBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.titleLayout.customerCount.text = "Customer Count : 03"
        bd.titleLayout.search.setOnClickListener {
            openSearchDialog()
        }
//        bd.recyclerView.adapter = AdapterRVReportVisit(this).apply {
//            setList(arrayListOf<VisitReportsModel>().apply {
//                add(
//                    VisitReportsModel(
//                        "Anum Empire, Shahrah-e-Faisal",
//                        "Visit Date : 2-1-2022",
//                        "Aftab Qureshi",
//                        "11:00"
//                    )
//                )
//                add(
//                    VisitReportsModel(
//                        "Anum Empire, Shahrah-e-Faisal",
//                        "Visit Date : 2-1-2022",
//                        "Aftab Qureshi",
//                        "11:00"
//                    )
//                )
//                add(
//                    VisitReportsModel(
//                        "Anum Empire, Shahrah-e-Faisal",
//                        "Visit Date : 2-1-2022",
//                        "Aftab Qureshi",
//                        "11:00"
//                    )
//                )
//            })
//        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
//        val reportItem = data as VisitReportsModel
//        navigateToFragment(R.id.action_reportVisit_to_checkin,Bundle().apply {
//            putSerializable("data",reportItem)
//        })
    }


}