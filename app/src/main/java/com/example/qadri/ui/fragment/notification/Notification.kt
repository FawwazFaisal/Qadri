package com.example.qadri.ui.fragment.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.FragmentNotificationBinding
import com.example.qadri.mvvm.model.notification.NotificationModel
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.createOrder.adapter.AdapterRvCartProducts

class Notification : BaseDockFragment(), ClickListener {

    lateinit var bd:FragmentNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentNotificationBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.searchBar.customerCount.text = "Notifications : 03"
        bd.recyclerView.adapter = AdapterRvNotification(this).apply {
            setList(arrayListOf<NotificationModel>().apply {
                add(NotificationModel("New notification received","2nd January 2022","12:00 PM"))
                add(NotificationModel("New notification received","2nd January 2022","12:00 PM"))
                add(NotificationModel("New notification received","2nd January 2022","12:00 PM"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {

    }
}