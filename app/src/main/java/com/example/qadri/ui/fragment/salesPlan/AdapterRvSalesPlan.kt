package com.example.qadri.ui.fragment.salesPlan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.ItemSalesPlanBinding
import com.example.qadri.mvvm.model.salesPlan.SalesPlanModel

class AdapterRvSalesPlan(val listener : ClickListener) : RecyclerView.Adapter<AdapterRvSalesPlan.SalesPlanViewHolder>() {
    inner class SalesPlanViewHolder(val bd : ItemSalesPlanBinding) : RecyclerView.ViewHolder(bd.root)

    val salesPlanList = arrayListOf<SalesPlanModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesPlanViewHolder {
        val bd = ItemSalesPlanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SalesPlanViewHolder(bd)
    }

    override fun onBindViewHolder(holder: SalesPlanViewHolder, position: Int) {
        val item = salesPlanList[position]
        holder.bd.name.text = item.name
        holder.bd.type.text = item.type
        holder.bd.address.text = item.address
        holder.bd.root.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return salesPlanList.size
    }

    fun setList(list : ArrayList<SalesPlanModel>){
        salesPlanList.clear()
        salesPlanList.addAll(list)
        notifyDataSetChanged()
    }
}