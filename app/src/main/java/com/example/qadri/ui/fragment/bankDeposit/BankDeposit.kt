package com.example.qadri.ui.fragment.bankDeposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.DialogAddBankDepositBinding
import com.example.qadri.databinding.DialogReportBankDepositDetailBinding
import com.example.qadri.databinding.FragmentBankDepositBinding
import com.example.qadri.mvvm.model.bankDeposit.BankDepositModel
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.bankDeposit.adapter.AdapterRvBankDeposit
import com.google.android.material.bottomsheet.BottomSheetDialog

class BankDeposit : BaseDockFragment(), ClickListener {

    lateinit var bd: FragmentBankDepositBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentBankDepositBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter = AdapterRvBankDeposit(this).apply {
            setList(arrayListOf<BankDepositModel>().apply {
                add(BankDepositModel("Usman Bukhari", "2-1-2022", "70,000"))
                add(BankDepositModel("Usman Bukhari", "2-1-2022", "70,000"))
                add(BankDepositModel("Usman Bukhari", "2-1-2022", "70,000"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        val item = data as BankDepositModel
        showAddPaymentDialog()
    }

    private fun showAddPaymentDialog() {
        BottomSheetDialog(requireContext(), R.style.SheetDialog).apply {
            val bd = DialogAddBankDepositBinding.inflate(layoutInflater)
            bd.btnSubmit.setOnClickListener {
                dismiss()
            }
            setContentView(bd.root)
        }.show()
    }
}