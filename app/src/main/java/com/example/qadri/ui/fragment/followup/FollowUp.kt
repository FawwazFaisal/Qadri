package com.example.qadri.ui.fragment.followup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.FragmentFollowUpBinding
import com.example.qadri.mvvm.model.bankDeposit.BankDepositModel
import com.example.qadri.mvvm.model.followup.FollowupModel
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.bankDeposit.adapter.AdapterRvBankDeposit

class FollowUp : BaseDockFragment(), ClickListener {

    lateinit var bd: FragmentFollowUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentFollowUpBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter = AdapterRVFollowUp(this).apply {
            setList(arrayListOf<FollowupModel>().apply {
                add(FollowupModel("Anum Estate Building, Shahrah-e-Faisal, Karachi", "Follow-up Date: 2-5-2022","Usman Bukhari", "Phone: 03205510451","Visit Date: 2-1-2022"))
                add(FollowupModel("Anum Estate Building, Shahrah-e-Faisal, Karachi", "Follow-up Date: 2-5-2022", "Usman Bukhari","Phone: 03205510451","Visit Date: 2-1-2022"))
                add(FollowupModel("Anum Estate Building, Shahrah-e-Faisal, Karachi", "Follow-up Date: 2-5-2022", "Usman Bukhari","Phone: 03205510451","Visit Date: 2-1-2022"))
            })
        }
        bd.searchBar.search.setOnClickListener {
            openSearchDialog()
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {

    }
}