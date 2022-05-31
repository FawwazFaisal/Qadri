package com.example.qadri.ui.fragment.createOrder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.R
import com.example.qadri.hilt.base.ClickListener
import com.example.qadri.databinding.ItemCustomerBinding
import com.example.qadri.mvvm.model.salesPlan.SalesPlanModel

class CreateOrderCustomerAdapter (val context: Context?, val listener: ClickListener) :
    RecyclerView.Adapter<CreateOrderCustomerAdapter.ViewHolder>() {

    private lateinit var dataList: List<SalesPlanModel>
    lateinit var view: ItemCustomerBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: SalesPlanModel) {

            view.name.text = (item.name ?: "N/A")
            view.type.text = (item.type ?: "N/A")
            view.address.text = (item.address ?: "N/A")

        }

    }

    fun setList(list: List<SalesPlanModel>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = ItemCustomerBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bindItems(item)

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }

        holder.itemView.animation =
            AnimationUtils.loadAnimation(context, R.anim.slide_transition_animation)
        holder.setIsRecyclable(false)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return when {
            ::dataList.isInitialized -> dataList.size
            else -> 0
        }
    }
}