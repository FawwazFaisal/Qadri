package com.example.qadri.ui.fragment.customerDetail.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.databinding.FragmentOrderDetailReportBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.customerDetail.tabs.adapter.AdapterRvBookedOrderProducts

class OrderDetails : BaseDockFragment() {

    lateinit var bd:FragmentOrderDetailReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentOrderDetailReportBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter = AdapterRvBookedOrderProducts().apply {
//            setList(arrayListOf<OrderDetailModel>().apply {
//                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//            })
        }
    }
}