package com.example.qadri.ui.fragment.createOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.databinding.CreateOrderHostFragmentBinding
import com.example.qadri.ui.activity.MainActivity
import com.example.qadri.ui.adapter.ViewPagerAdapter

class CreateOrderHostFragment : Fragment() {

    lateinit var binding: CreateOrderHostFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = CreateOrderHostFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = arguments?.getString("title")
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager).apply {
            addFragment(CreateOrderProductFragment(),"Artificial Leather")
            addFragment(CreateOrderProductFragment(),"Contact Adhesive")
            addFragment(CreateOrderProductFragment(),"Spare Parts")
        }
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}