package com.example.qadri.ui.fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.DialogComplaintBinding
import com.example.qadri.databinding.DialogCompletedOrderBinding
import com.example.qadri.databinding.DialogTransitOrderDetailBinding
import com.example.qadri.databinding.TransitOrderFragmentBinding
import com.example.qadri.ui.fragment.order.adapter.TransitOrderAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchBar.customerCount.text = "Total Orders: 03"
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
        showTransitDetailDialog()
    }

    private fun showTransitDetailDialog() {
        BottomSheetDialog(requireContext(), R.style.SheetDialog).apply {
            val bd = DialogTransitOrderDetailBinding.inflate(layoutInflater)
            bd.complainBtn.setOnClickListener {
                dismiss()
                showComplaintDialog()
            }
            setContentView(bd.root)
        }.show()
    }

    private fun showComplaintDialog() {
        BottomSheetDialog(requireContext(), R.style.SheetDialog).apply {
            val bd = DialogComplaintBinding.inflate(layoutInflater)
            bd.btnSubmit.setOnClickListener {
                dismiss()
                showTransitDetailDialog()
            }
            setContentView(bd.root)
        }.show()
    }
}