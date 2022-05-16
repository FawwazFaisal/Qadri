package com.example.qadri.ui.fragment.customerDetail.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.qadri.R
import com.example.qadri.databinding.FragmentCustomerComplaintsBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.customerDetail.tabs.adapter.AdapterRvBookedOrderProducts
import com.example.qadri.ui.fragment.customerDetail.tabs.adapter.AdapterRvReturnProducts


class CustomerFeedback : BaseDockFragment() {

    lateinit var bd: FragmentCustomerComplaintsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentCustomerComplaintsBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.radioGrp.setOnCheckedChangeListener { radioGroup, i ->
            bd.radioGrp.children.forEach {
                if(it is RadioButton){
                    if(it.isChecked){
                        it.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        if(it.id==bd.radioComplaint.id){
                            showComplaintForm()
                        }else{
                            showReturnForm()
                        }
                    }else{
                        it.setTextColor(ContextCompat.getColor(requireContext(),R.color.black_light))
                    }
                }
            }
        }
        bd.radioComplaint.isChecked = true
    }

    private fun showReturnForm() {
        bd.returnView.visibility = View.VISIBLE
        bd.complaintView.visibility = View.GONE
        bd.btnReturns.setOnClickListener {
            if((it as Button).text.contentEquals("search",true)){
                bd.reasonViewChild.visibility = View.VISIBLE
                bd.recyclerView.adapter = AdapterRvReturnProducts().apply {
//                    setList(arrayListOf<OrderDetailModel>().apply {
//                        add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//                        add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//                        add(OrderDetailModel("3RD Design (BURNISH OFF) 0.9mm Black","500"))
//                    })
                }
                it.text = "Request Refund"
            }else{
                bd.reasonViewChild.visibility = View.GONE
                it.text = "Search"
            }
        }
    }

    private fun showComplaintForm() {
        bd.complaintView.visibility = View.VISIBLE
        bd.returnView.visibility = View.GONE
    }
}