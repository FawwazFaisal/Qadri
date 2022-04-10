package com.example.qadri.ui.fragment.reports.recovery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.ItemReportRecoveryBinding
import com.example.qadri.mvvm.model.reports.RecoveryReportModel

class AdapterRvRecovery(val listener:ClickListener) : RecyclerView.Adapter<AdapterRvRecovery.RecoveryHolder>() {

    lateinit var context: Context
    val recoveryList = arrayListOf<RecoveryReportModel>()

    inner class RecoveryHolder(val bd: ItemReportRecoveryBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecoveryHolder {
        val bd =
            ItemReportRecoveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecoveryHolder(bd)
    }

    override fun onBindViewHolder(holder: RecoveryHolder, position: Int) {
        val item = recoveryList[position]
        holder.bd.name.text = item.name
        holder.bd.phone.text = item.phone
        holder.bd.amount.text = item.amount
        holder.bd.type.text = item.type
        holder.bd.root.setOnClickListener {
            listener.onClick(item)
        }
    }

    fun setList(list: ArrayList<RecoveryReportModel>){
        recoveryList.clear()
        recoveryList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recoveryList.size
    }
}