package com.example.qadri.ui.fragment.reports.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.databinding.FragmentReportOrderDetailBinding
import com.example.qadri.mvvm.model.reports.OrderDetailModel
import com.example.qadri.mvvm.model.reports.RecoveryReportModel
import com.example.qadri.ui.activity.MainActivity
import com.example.qadri.ui.fragment.BaseDockFragment


class ReportOrderDetail : BaseDockFragment() {

    lateinit var bd:FragmentReportOrderDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentReportOrderDetailBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val order = arguments?.getSerializable("data") as RecoveryReportModel
        (requireActivity() as MainActivity).supportActionBar?.title = order.name

        bd.recyclerView.adapter = AdapterRvOrderDetail().apply {
            setList(arrayListOf<OrderDetailModel>().apply {
                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
            })
        }
    }
}