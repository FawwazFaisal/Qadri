package com.example.qadri.ui.fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.TransitOrderFragmentBinding
import com.example.qadri.ui.fragment.order.adapter.TransitOrderAdapter

class TransitOrderFragment : Fragment(), ClickListener {

    lateinit var binding: TransitOrderFragmentBinding
    var dataList = ArrayList<DummyPendingOrder>()
    lateinit var adapter: TransitOrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        binding = TransitOrderFragmentBinding.inflate(layoutInflater)
        dataList.add(DummyPendingOrder("ORDER# 001"))
        dataList.add(DummyPendingOrder("ORDER# 002"))
        dataList.add(DummyPendingOrder("ORDER# 003"))
        initRecyclerView(dataList)
    }

    private fun initRecyclerView(list: List<DummyPendingOrder>){
        adapter = TransitOrderAdapter(requireContext(), this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter
    }

    override fun <T> onClick(data: T, createNested: Boolean) {

    }
}