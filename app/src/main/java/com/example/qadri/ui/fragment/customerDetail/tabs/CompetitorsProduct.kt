package com.example.qadri.ui.fragment.customerDetail.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentCompetitorsProductBinding
import com.example.qadri.ui.fragment.BaseDockFragment


class CompetitorsProduct : BaseDockFragment() {

    lateinit var bd: FragmentCompetitorsProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentCompetitorsProductBinding.inflate(layoutInflater)
        return bd.root
    }
}