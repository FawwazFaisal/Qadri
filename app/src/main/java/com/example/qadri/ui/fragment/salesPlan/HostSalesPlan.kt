package com.example.qadri.ui.fragment.salesPlan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.databinding.FragmentHostSalesPlanBinding
import com.example.qadri.ui.adapter.ViewPagerAdapter
import com.example.qadri.ui.fragment.BaseDockFragment

class HostSalesPlan : BaseDockFragment() {

    lateinit var bd: FragmentHostSalesPlanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentHostSalesPlanBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.viewPager.adapter = ViewPagerAdapter(childFragmentManager).apply {
            addFragment(SalesPlanTab(), "Today's Plan")
            addFragment(SalesPlanTab(), "Pending Visits")
            addFragment(SalesPlanTab(), "Other Visits")
            addFragment(SalesPlanTab(), "Completed Visits")
        }
        bd.tabLayout.setupWithViewPager(bd.viewPager)
    }
}