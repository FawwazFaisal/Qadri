package com.example.qadri.ui.fragment.reports.bankDeposit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.ItemReportAgingBinding
import com.example.qadri.databinding.ItemReportBankDepositBinding
import com.example.qadri.mvvm.model.reports.ReportAgingModel
import com.example.qadri.mvvm.model.reports.ReportBankDepositModel

class AdapterRvReportBankDeposit(val listener:ClickListener) : RecyclerView.Adapter<AdapterRvReportBankDeposit.AgingHolder>() {

    lateinit var context: Context
    val recoveryList = arrayListOf<ReportBankDepositModel>()

    inner class AgingHolder(val bd: ItemReportBankDepositBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgingHolder {
        val bd =
            ItemReportBankDepositBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgingHolder(bd)
    }

    override fun onBindViewHolder(holder: AgingHolder, position: Int) {
        val item = recoveryList[position]
        holder.bd.name.text = item.name
        holder.bd.date.text = item.date
        holder.bd.amount.text = item.amount
        holder.bd.root.setOnClickListener {
            listener.onClick(item)
        }
    }

    fun setList(list: ArrayList<ReportBankDepositModel>){
        recoveryList.clear()
        recoveryList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recoveryList.size
    }
}