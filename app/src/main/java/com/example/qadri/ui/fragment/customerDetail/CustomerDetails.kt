package com.example.qadri.ui.fragment.customerDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentCustomerDetailBinding
import com.example.qadri.ui.activity.MainActivity
import com.example.qadri.ui.adapter.AdapterGenericVp
import com.example.qadri.ui.adapter.ViewPagerAdapter
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.customerDetail.tabs.*

class CustomerDetails : BaseDockFragment() {

    lateinit var bd : FragmentCustomerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentCustomerDetailBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = arguments?.getString("title")
        bd.viewPager.adapter = ViewPagerAdapter(childFragmentManager).apply {
            addFragment(CustomerInfo(),"Customer Info")
            addFragment(PreviousVisit(),"Previous Visits")
            addFragment(AccountsReceivables(),"Account/Receivables")
            addFragment(BookedOrders(),"Booked Orders")
            addFragment(CustomerFeedback(),"Customer Feedback")
            addFragment(UpdateLocation(),"Update Location")
            addFragment(CompetitorsProduct(),"Competitor's Product")
        }
        bd.tabLayout.setupWithViewPager(bd.viewPager)

        bd.customerActions.notes.setOnClickListener {
            navigateToFragment(R.id.action_customerDetail_to_customerNotes)
        }
        bd.customerActions.recovery.setOnClickListener {
            navigateToFragment(R.id.action_customerDetail_to_recoveryForm)
        }
        bd.customerActions.checkIn.setOnClickListener {
            navigateToFragment(R.id.action_customerDetail_to_checkIn)
        }
    }
}