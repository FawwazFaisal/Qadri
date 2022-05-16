package com.example.qadri.ui.fragment.customerDetail.tabs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.databinding.ItemReportOrderDetailBinding

class AdapterRvBookedOrderProducts() : RecyclerView.Adapter<AdapterRvBookedOrderProducts.RecoveryHolder>() {

    lateinit var context: Context
    val recoveryList = listOf<String>()

    inner class RecoveryHolder(val bd: ItemReportOrderDetailBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecoveryHolder {
        val bd =
            ItemReportOrderDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecoveryHolder(bd)
    }

    override fun onBindViewHolder(holder: RecoveryHolder, position: Int) {
        val item = recoveryList[position]
    }

//    fun setList(list: ArrayList<OrderDetailModel>){
//        recoveryList.clear()
//        recoveryList.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return recoveryList.size
    }
}