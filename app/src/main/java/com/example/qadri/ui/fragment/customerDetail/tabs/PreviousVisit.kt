package com.example.qadri.ui.fragment.customerDetail.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.FragmentPreviousVisitBinding
import com.example.qadri.mvvm.model.previousVisit.PreviousVisitModel
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.customerDetail.tabs.adapter.AdapterRvPreviousVisit

class PreviousVisit : BaseDockFragment(), ClickListener {

    lateinit var bd: FragmentPreviousVisitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentPreviousVisitBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter = AdapterRvPreviousVisit(this).apply {
            setList(arrayListOf<PreviousVisitModel>().apply {
                add(PreviousVisitModel("Usman Bukharist","01-01-2022","3:00 PM","Anum Empire, Shahrah - Faisal, Karachi"))
                add(PreviousVisitModel("Usman Bukharist","01-01-2022","3:00 PM","Anum Empire, Shahrah - Faisal, Karachi"))
                add(PreviousVisitModel("Usman Bukharist","01-01-2022","3:00 PM","Anum Empire, Shahrah - Faisal, Karachi"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {

    }
}