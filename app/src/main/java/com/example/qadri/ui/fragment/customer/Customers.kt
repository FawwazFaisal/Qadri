package com.example.qadri.ui.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.FragmentCustomerBinding
import com.example.qadri.mvvm.model.salesPlan.SalesPlanModel
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.customer.adapter.AdapterRvCustomer

class Customers : BaseDockFragment(), ClickListener {

    lateinit var bd : FragmentCustomerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentCustomerBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.addBtn.setOnClickListener{
            navigateToFragment(R.id.action_customers_to_addCustomer)
        }
        initRecyclerView()
    }

    private fun initRecyclerView(){
        bd.recyclerView.adapter = AdapterRvCustomer(requireContext(),this).apply {
            setList(arrayListOf<SalesPlanModel>().apply {
                add(SalesPlanModel("Anum Estate Building, Shahrah-e-Faisal","Fawwaz","A+ Category"))
                add(SalesPlanModel("Anum Estate Building, Shahrah-e-Faisal","Fawwaz","A+ Category"))
                add(SalesPlanModel("Anum Estate Building, Shahrah-e-Faisal","Fawwaz","A+ Category"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        navigateToFragment(R.id.action_customers_to_customerDetail,Bundle().apply {
            putString("title",(data as SalesPlanModel).name)
        })
    }
}