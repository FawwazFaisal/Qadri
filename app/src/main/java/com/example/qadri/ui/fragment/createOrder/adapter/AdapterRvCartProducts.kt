package com.example.qadri.ui.fragment.createOrder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.databinding.ItemReportOrderDetailBinding

class AdapterRvCartProducts() : RecyclerView.Adapter<AdapterRvCartProducts.RecoveryHolder>() {

    lateinit var context: Context
//    val recoveryList = arrayListOf<OrderDetailModel>()

    inner class RecoveryHolder(val bd: ItemReportOrderDetailBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecoveryHolder {
        val bd =
            ItemReportOrderDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecoveryHolder(bd)
    }

    override fun onBindViewHolder(holder: RecoveryHolder, position: Int) {
//        val item = recoveryList[position]
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

//    fun setList(list: ArrayList<OrderDetailModel>){
//        recoveryList.clear()
//        recoveryList.addAll(list)
//        notifyDataSetChanged()
//    }

//    override fun getItemCount(): Int {
//        return recoveryList.size
//    }
}