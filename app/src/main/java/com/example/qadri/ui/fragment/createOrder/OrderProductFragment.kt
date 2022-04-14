package com.example.qadri.ui.fragment.createOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.CreateOrderHostFragmentBinding
import com.example.qadri.databinding.OrderProductFragmentBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.createOrder.adapter.OrderProductAdapter
import com.example.qadri.ui.fragment.order.DummyPendingOrder
import com.example.qadri.ui.fragment.order.adapter.PendingOrderAdapter

class OrderProductFragment : BaseDockFragment(), ClickListener {

    lateinit var binding: OrderProductFragmentBinding
    lateinit var adapter: OrderProductAdapter
    var dataList = ArrayList<DummyPendingOrder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        binding = OrderProductFragmentBinding.inflate(layoutInflater)
        dataList.add(DummyPendingOrder("3RD Design (BURNISH OFF) 0.9 mm Black"))
        dataList.add(DummyPendingOrder("3RD Design (BURNISH OFF) 0.9 mm Brown"))
        dataList.add(DummyPendingOrder("3RD Design (BURNISH OFF) 0.9 mm Mustard"))

        initRecyclerView(dataList)

        binding.viewCartBtn.setOnClickListener {
            navigateToFragment(R.id.action_nav_create_order_host_fragment_to_view_cart)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataList.clear()
    }


    private fun initRecyclerView(list: List<DummyPendingOrder>){
        adapter = OrderProductAdapter(requireContext(), this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
    }
}