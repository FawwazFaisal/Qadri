package com.example.qadri.ui.fragment.createOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.DialogPendingOrderDetailBinding
import com.example.qadri.databinding.TotalCartBottomSheetBinding
import com.example.qadri.databinding.ViewCartOrderFragmentBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class ViewCartOrderFragment : Fragment() {

    lateinit var binding: ViewCartOrderFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        binding = ViewCartOrderFragmentBinding.inflate(layoutInflater)

      //  bottomSheetDialog()
    }

    fun bottomSheetDialog() {
        val alertDialog = BottomSheetDialog(requireContext(),R.style.SheetDialogWithoutTranslucent)
        val viewBinding = TotalCartBottomSheetBinding.inflate(layoutInflater)
        alertDialog.setContentView(viewBinding.root)
        alertDialog.setCancelable(false)
        alertDialog.show()

    }
}