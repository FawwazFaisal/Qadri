package com.example.qadri.ui.fragment.order.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.ItemPendingOrderBinding
import com.example.qadri.ui.fragment.order.DummyPendingOrder
import kotlinx.android.synthetic.main.item_pending_order.view.*

class TransitOrderAdapter (val context: Context?, val listener: ClickListener) :
    RecyclerView.Adapter<TransitOrderAdapter.ViewHolder>() {

    private lateinit var dataList: List<DummyPendingOrder>
    lateinit var view: ItemPendingOrderBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: DummyPendingOrder) {
            view.orderName.text = (item.name ?: "N/A")
        }
    }

    fun setList(list: List<DummyPendingOrder>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = ItemPendingOrderBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bindItems(item)

        holder.itemView.viewIv.setOnClickListener {
            holder.itemView.ll_detail.visibility = View.VISIBLE
            holder.itemView.complain_btn.visibility = View.VISIBLE
            holder.itemView.cancelIv.visibility = View.VISIBLE
            holder.itemView.viewIv.visibility = View.GONE
        }

        holder.itemView.cancelIv.setOnClickListener {
            holder.itemView.ll_detail.visibility = View.GONE
            holder.itemView.cancelIv.visibility = View.GONE
            holder.itemView.complain_btn.visibility = View.GONE
            holder.itemView.viewIv.visibility = View.VISIBLE
        }

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