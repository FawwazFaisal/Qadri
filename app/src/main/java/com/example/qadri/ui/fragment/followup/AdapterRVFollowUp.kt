package com.example.qadri.ui.fragment.followup

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.ItemFollowUpBinding
import com.example.qadri.mvvm.model.bankDeposit.BankDepositModel
import com.example.qadri.mvvm.model.followup.FollowupModel

class AdapterRVFollowUp (val listener: ClickListener) : RecyclerView.Adapter<AdapterRVFollowUp.FollowupHolder>() {

    lateinit var context: Context
    val followUps = arrayListOf<FollowupModel>()

    inner class FollowupHolder(val bd: ItemFollowUpBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowupHolder {
        val bd =
            ItemFollowUpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowupHolder(bd)
    }

    override fun onBindViewHolder(holder: FollowupHolder, position: Int) {
        val item = followUps[position]
        holder.bd.name.text = item.name
        holder.bd.visitDate.text = item.visit
        holder.bd.followUpDate.text = item.followup
        holder.bd.address.text = item.address
        holder.bd.phoneNo.text = item.phone
        holder.bd.root.setOnClickListener {
            listener.onClick(item)
        }
    }

    fun setList(list: ArrayList<FollowupModel>){
        followUps.clear()
        followUps.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return followUps.size
    }
}