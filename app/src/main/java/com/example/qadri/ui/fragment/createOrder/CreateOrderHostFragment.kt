package com.example.qadri.ui.fragment.createOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.CreateOrderHostFragmentBinding
import com.example.qadri.ui.adapter.ViewPagerAdapter
import com.example.qadri.ui.fragment.salesPlan.SalesPlanTab

class CreateOrderHostFragment : Fragment() {

    lateinit var binding: CreateOrderHostFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        binding = CreateOrderHostFragmentBinding.inflate(layoutInflater)

        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager).apply {
            addFragment(OrderProductFragment(),"Artificial Leather")
            addFragment(OrderProductFragment(),"Contact Adhesive")
            addFragment(OrderProductFragment(),"Spare Parts")
        }
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}