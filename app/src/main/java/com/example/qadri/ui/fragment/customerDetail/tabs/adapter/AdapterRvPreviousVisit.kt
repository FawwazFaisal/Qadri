package com.example.qadri.ui.fragment.customerDetail.tabs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.ItemPreviousVisitBinding

class AdapterRvPreviousVisit(listener: ClickListener) :
    RecyclerView.Adapter<AdapterRvPreviousVisit.PreviousVisitHolder>() {

    val previousVisitList = listOf<String>()

    inner class PreviousVisitHolder(val bd: ItemPreviousVisitBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousVisitHolder {
        val bd =
            ItemPreviousVisitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PreviousVisitHolder(bd)
    }

    override fun onBindViewHolder(holder: PreviousVisitHolder, position: Int) {
        val item = previousVisitList[position]
//        holder.bd.name.text = item.visitedBy
//        holder.bd.date.text = item.vistedDate
//        holder.bd.time.text = item.vistedTime
//        holder.bd.address.text = item.address
    }
//    fun setList(arrayList: ArrayList<PreviousVisitModel>){
//        previousVisitList.clear()
//        previousVisitList.addAll(arrayList)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return previousVisitList.size
    }
}