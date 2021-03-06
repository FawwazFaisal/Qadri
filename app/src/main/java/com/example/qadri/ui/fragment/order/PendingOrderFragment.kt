package com.example.qadri.ui.fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.DialogPendingOrderDetailBinding
import com.example.qadri.databinding.PendingOrderFragmentBinding
import com.example.qadri.ui.fragment.order.adapter.PendingOrderAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

data class DummyPendingOrder(
    var name: String = ""
)

class PendingOrderFragment : Fragment(), ClickListener {

    lateinit var binding: PendingOrderFragmentBinding
    var dataList = ArrayList<DummyPendingOrder>()
    lateinit var adapter: PendingOrderAdapter

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
        binding = PendingOrderFragmentBinding.inflate(layoutInflater)
        dataList.add(DummyPendingOrder("ORDER# 001"))
        dataList.add(DummyPendingOrder("ORDER# 002"))
        dataList.add(DummyPendingOrder("ORDER# 003"))
        initRecyclerView(dataList)
    }

    private fun initRecyclerView(list: List<DummyPendingOrder>){
        adapter = PendingOrderAdapter(requireContext(), this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataList.clear()
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        bottomSheetDialog()
    }

    fun bottomSheetDialog() {
        val alertDialog = BottomSheetDialog(requireContext(),R.style.SheetDialog)
        val viewBinding = DialogPendingOrderDetailBinding.inflate(layoutInflater)
        alertDialog.setContentView(viewBinding.root)
        alertDialog.setCancelable(true)
        alertDialog.show()

    }
}