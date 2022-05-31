package com.example.qadri.ui.fragment.reports.aging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.ItemReportAgingBinding

class AdapterRvAging(val listener:ClickListener) : RecyclerView.Adapter<AdapterRvAging.AgingHolder>() {

    lateinit var context: Context
    val recoveryList = listOf<String>()

    inner class AgingHolder(val bd: ItemReportAgingBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgingHolder {
        val bd =
            ItemReportAgingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgingHolder(bd)
    }

    override fun onBindViewHolder(holder: AgingHolder, position: Int) {
        val item = recoveryList[position]
//        holder.bd.name.text = item.name
//        holder.bd.overdue.text = item.overdue
//        holder.bd.total.text = item.total
        holder.bd.root.setOnClickListener {
            listener.onClick(item)
        }
    }

//    fun setList(list: ArrayList<ReportAgingModel>){
//        recoveryList.clear()
//        recoveryList.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return recoveryList.size
    }
}