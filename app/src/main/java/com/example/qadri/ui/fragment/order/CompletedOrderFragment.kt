package com.example.qadri.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.DialogCompletedOrderBinding
import com.example.qadri.databinding.FragmentCompletedOrderBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.order.adapter.AdapterRvCompleted
import com.google.android.material.bottomsheet.BottomSheetDialog


class CompletedOrderFragment : BaseDockFragment(), ClickListener {

    lateinit var bd: FragmentCompletedOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentCompletedOrderBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.searchBar.customerCount.text = "Total Orders: 03"
        bd.recyclerView.adapter = AdapterRvCompleted(requireContext(), this).apply {
            setList(arrayListOf<DummyPendingOrder>().apply {
                add(DummyPendingOrder("ORDER# 001"))
                add(DummyPendingOrder("ORDER# 002"))
                add(DummyPendingOrder("ORDER# 003"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        val item = data as DummyPendingOrder
        showOrderDetailsDialog()
    }

    private fun showOrderDetailsDialog() {
        BottomSheetDialog(requireContext()).apply {
            val bd = DialogCompletedOrderBinding.inflate(layoutInflater)
            setContentView(bd.root)
        }.show()
    }
}