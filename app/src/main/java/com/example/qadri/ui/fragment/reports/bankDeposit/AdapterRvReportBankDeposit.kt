package com.example.qadri.ui.fragment.reports.bankDeposit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.ItemReportBankDepositBinding

class AdapterRvReportBankDeposit(val listener:ClickListener) : RecyclerView.Adapter<AdapterRvReportBankDeposit.BankDepositHolder>() {

    lateinit var context: Context
    val recoveryList = listOf<String>()

    inner class BankDepositHolder(val bd: ItemReportBankDepositBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankDepositHolder {
        val bd =
            ItemReportBankDepositBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BankDepositHolder(bd)
    }

    override fun onBindViewHolder(holder: BankDepositHolder, position: Int) {
        val item = recoveryList[position]
//        holder.bd.name.text = item.name
//        holder.bd.date.text = item.date
//        holder.bd.amount.text = item.amount
        holder.bd.root.setOnClickListener {
            listener.onClick(item)
        }
    }

//    fun setList(list: ArrayList<ReportBankDepositModel>){
//        recoveryList.clear()
//        recoveryList.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return recoveryList.size
    }
}