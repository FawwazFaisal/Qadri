package com.example.qadri.ui.fragment.bankDeposit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.ItemReportBankDepositBinding

class AdapterRvBankDeposit(val listener: ClickListener) : RecyclerView.Adapter<AdapterRvBankDeposit.BankDepositHolder>() {

    lateinit var context: Context
    val banksDeposits = listOf<String>()

    inner class BankDepositHolder(val bd: ItemReportBankDepositBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankDepositHolder {
        val bd =
            ItemReportBankDepositBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BankDepositHolder(bd)
    }

    override fun onBindViewHolder(holder: BankDepositHolder, position: Int) {
//        val item = banksDeposits[position]
//        holder.bd.name.text = item.name
//        holder.bd.date.text = item.date
//        holder.bd.amount.text = item.amount
//        holder.bd.root.setOnClickListener {
//            listener.onClick(item)
//        }
    }

//    fun setList(list: ArrayList<BankDepositModel>){
//        banksDeposits.clear()
//        banksDeposits.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return banksDeposits.size
    }
}