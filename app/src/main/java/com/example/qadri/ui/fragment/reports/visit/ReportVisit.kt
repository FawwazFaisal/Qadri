package com.example.qadri.ui.fragment.reports.visit

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.DialogFilterReportsBinding
import com.example.qadri.databinding.FragmentReportVisitBinding
import com.example.qadri.mvvm.model.reports.ReportsModel
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
        bd.searchLayout.customerCount.text = "Customer Count : 03"
        bd.searchLayout.search.setOnClickListener {
            openSearchDialog()
        }
        bd.recyclerView.adapter = AdapterRVReportVisit(this).apply {
            setList(arrayListOf<ReportsModel>().apply {
                add(
                    ReportsModel(
                        "Anum Empire, Shahrah-e-Faisal",
                        "Visit Date : 2-1-2022",
                        "Aftab Qureshi",
                        "11:00"
                    )
                )
                add(
                    ReportsModel(
                        "Anum Empire, Shahrah-e-Faisal",
                        "Visit Date : 2-1-2022",
                        "Aftab Qureshi",
                        "11:00"
                    )
                )
                add(
                    ReportsModel(
                        "Anum Empire, Shahrah-e-Faisal",
                        "Visit Date : 2-1-2022",
                        "Aftab Qureshi",
                        "11:00"
                    )
                )
            })
        }
    }

    private fun openSearchDialog() {
        val searchDialog = Dialog(requireContext())
        searchDialog.window?.setBackgroundDrawableResource(R.color.zxing_transparent)
        val bd = DialogFilterReportsBinding.inflate(layoutInflater)
        searchDialog.setContentView(bd.root)
        bd.radioGrp.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioDate -> {
                    bd.dateContainer.visibility = View.VISIBLE
                    bd.name.visibility = View.GONE
                }
                R.id.radioName -> {
                    bd.dateContainer.visibility = View.GONE
                    bd.name.visibility = View.VISIBLE
                }
            }
        }
        bd.btnSearch.setOnClickListener {
            searchDialog.dismiss()
        }
        searchDialog.show()
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        val reportItem = data as ReportsModel
        navigateToFragment(R.id.action_reportVisit_to_visitLogDetail,Bundle().apply {
            putSerializable("data",reportItem)
        })
    }


}