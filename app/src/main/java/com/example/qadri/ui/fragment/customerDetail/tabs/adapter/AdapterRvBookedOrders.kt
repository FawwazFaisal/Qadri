package com.example.qadri.ui.fragment.customerDetail.tabs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.ItemBookedOrderBinding
import com.example.qadri.mvvm.model.bookedOrder.BookedOrderModel

class AdapterRvBookedOrders(val listener:ClickListener) : RecyclerView.Adapter<AdapterRvBookedOrders.BookedOrderHolder>() {

    lateinit var context: Context
    val bookedOrderList = arrayListOf<BookedOrderModel>()

    inner class BookedOrderHolder(val bd: ItemBookedOrderBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedOrderHolder {
        val bd =
            ItemBookedOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookedOrderHolder(bd)
    }

    override fun onBindViewHolder(holder: BookedOrderHolder, position: Int) {
        val item = bookedOrderList[position]
        holder.bd.orderId.text = item.orderId
        holder.bd.time.text = item.time
        holder.bd.date.text = item.date
        holder.bd.qty.text = item.qty
        holder.bd.amount.text = item.amount
        holder.bd.deposit.text = item.deposit
        holder.bd.root.setOnClickListener {
            listener.onClick(item)
        }
    }

    fun setList(list: ArrayList<BookedOrderModel>){
        bookedOrderList.clear()
        bookedOrderList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return bookedOrderList.size
    }
}