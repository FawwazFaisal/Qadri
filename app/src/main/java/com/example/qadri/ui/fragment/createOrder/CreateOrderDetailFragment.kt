package com.example.qadri.ui.fragment.createOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.databinding.FragmentCartOrderDetailBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.createOrder.adapter.AdapterRvCartProducts

class CreateOrderDetailFragment : BaseDockFragment() {

    lateinit var bd:FragmentCartOrderDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentCartOrderDetailBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bd.recyclerView.adapter = AdapterRvCartProducts().apply {
//            setList(arrayListOf<OrderDetailModel>().apply {
//                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//                add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//            })
//        }
    }
}