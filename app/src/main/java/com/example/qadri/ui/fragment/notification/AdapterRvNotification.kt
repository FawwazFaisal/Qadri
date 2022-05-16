package com.example.qadri.ui.fragment.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.ItemNotificationBinding
import com.example.qadri.databinding.ItemReportOrderDetailBinding
import com.example.qadri.mvvm.model.notification.NotificationModel

class AdapterRvNotification(val listener: ClickListener) : RecyclerView.Adapter<AdapterRvNotification.NotificationHolder>() {

    lateinit var context: Context
    val notifications = arrayListOf<NotificationModel>()

    inner class NotificationHolder(val bd: ItemNotificationBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val bd =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationHolder(bd)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        val item = notifications[position]
    }

    fun setList(list: ArrayList<NotificationModel>){
        notifications.clear()
        notifications.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}