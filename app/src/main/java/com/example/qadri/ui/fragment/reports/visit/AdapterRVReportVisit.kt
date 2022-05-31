package com.example.qadri.ui.fragment.reports.visit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.ItemReportVisitBinding

class AdapterRVReportVisit(val listener:ClickListener): RecyclerView.Adapter<AdapterRVReportVisit.ReportViewHolder>() {

    lateinit var bd :ItemReportVisitBinding
    val listReports = listOf<String>()

    inner class ReportViewHolder(val bd : ItemReportVisitBinding) : RecyclerView.ViewHolder(bd.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        bd = ItemReportVisitBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReportViewHolder(bd)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
//        val item = listReports[position]
//        holder.bd.address.text = item.address
//        holder.bd.time.text = item.time
//        holder.bd.date.text = item.date
//        holder.bd.name.text = item.name
//        bd.root.setOnClickListener {
//            listener.onClick(item)
//        }
    }

//    fun setList(list :ArrayList<VisitReportsModel>){
//        listReports.clear()
//        listReports.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return listReports.size
    }
}