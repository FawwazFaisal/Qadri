package com.example.qadri.ui.fragment.createOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.CreateOrderCustomerFragmentBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.createOrder.adapter.CreateOrderCustomerAdapter
import com.example.qadri.ui.fragment.order.DummyPendingOrder
import com.example.qadri.ui.fragment.order.adapter.PendingOrderAdapter

class CreateOrderCustomerFragment : BaseDockFragment(), ClickListener {

    lateinit var binding: CreateOrderCustomerFragmentBinding
    var dataList = ArrayList<DummyPendingOrder>()
    lateinit var adapter: CreateOrderCustomerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        binding = CreateOrderCustomerFragmentBinding.inflate(layoutInflater)
        dataList.add(DummyPendingOrder("AR Traders"))
        dataList.add(DummyPendingOrder("3 M Brothers"))
        dataList.add(DummyPendingOrder("AZ Traders"))
        initRecyclerView(dataList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataList.clear()
    }

    private fun initRecyclerView(list: List<DummyPendingOrder>){
        adapter = CreateOrderCustomerAdapter(requireContext(), this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        navigateToFragment(R.id.create_order_host_fragment)
    }

}