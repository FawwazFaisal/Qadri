package com.example.qadri.ui.fragment.createOrder

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.qadri.R
import com.example.qadri.databinding.DialogDiscountBinding
import com.example.qadri.databinding.TotalCartBottomSheetBinding
import com.example.qadri.databinding.ViewCartOrderFragmentBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class Cart : BaseDockFragment() {

    lateinit var binding: ViewCartOrderFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewCartOrderFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConfirm.setOnClickListener {
            navigateToFragment(R.id.action_view_cart_fragment_to_orderDetailFragment)
        }
        binding.addDiscount.setOnClickListener {
            showDiscountDialog()
        }
    }

    private fun showDiscountDialog() {
        Dialog(requireContext()).apply {
            window?.setBackgroundDrawableResource(R.color.zxing_transparent)
            val bd = DialogDiscountBinding.inflate(layoutInflater)
            setContentView(bd.root)
            bd.btnSubmit.setOnClickListener {
                dismiss()
            }
        }.show()
    }
}