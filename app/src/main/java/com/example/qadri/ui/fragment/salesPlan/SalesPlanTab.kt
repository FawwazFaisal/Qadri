package com.example.qadri.ui.fragment.salesPlan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.FragmentSalesPlanTbBinding
import com.example.qadri.mvvm.model.salesPlan.SalesPlanModel
import com.example.qadri.ui.fragment.BaseDockFragment

class SalesPlanTab : BaseDockFragment(), ClickListener {

    lateinit var bd : FragmentSalesPlanTbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentSalesPlanTbBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter =AdapterRvSalesPlan(this).apply {
            setList(arrayListOf<SalesPlanModel>().apply {
                add(SalesPlanModel("Anum Estate Building, Shahrah-e-Faisal","Fawwaz","A+ Category"))
                add(SalesPlanModel("Anum Estate Building, Shahrah-e-Faisal","Fawwaz","A+ Category"))
                add(SalesPlanModel("Anum Estate Building, Shahrah-e-Faisal","Fawwaz","A+ Category"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        val item = data as SalesPlanModel
        navigateToFragment(R.id.action_hostSalesPlan_to_customerDetail,Bundle().apply {
            putString("title",item.name)
        })
    }
}