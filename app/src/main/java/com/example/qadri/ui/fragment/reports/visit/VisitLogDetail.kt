package com.example.qadri.ui.fragment.reports.visit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.databinding.FragmentVisitLogDetailBinding
import com.example.qadri.mvvm.model.reports.VisitReportsModel
import com.example.qadri.ui.activity.MainActivity
import com.example.qadri.ui.fragment.BaseDockFragment

class VisitLogDetail : BaseDockFragment() {

    lateinit var bd : FragmentVisitLogDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentVisitLogDetailBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = (arguments?.getSerializable("data") as VisitReportsModel)
        (requireActivity() as MainActivity).supportActionBar?.title = item.name
    }
}