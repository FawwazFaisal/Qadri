package com.example.qadri.ui.fragment.customerDetail.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.FragmentBookedOrdersBinding
import com.example.qadri.mvvm.model.bookedOrder.BookedOrderModel
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.customerDetail.tabs.adapter.AdapterRvBookedOrders


class BookedOrders : BaseDockFragment(), ClickListener {

    lateinit var bd: FragmentBookedOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentBookedOrdersBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter = AdapterRvBookedOrders(this).apply {
            setList(arrayListOf<BookedOrderModel>().apply {
                add(BookedOrderModel("4589","12:00 PM","5-2-2022","3","500,000","20K"))
                add(BookedOrderModel("4589","12:00 PM","5-2-2022","3","500,000","100K"))
                add(BookedOrderModel("4589","12:00 PM","5-2-2022","3","500,000","50K"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        val item = data as BookedOrderModel
        navigateToFragment(R.id.action_customerDetail_to_orderDetails)
    }


}