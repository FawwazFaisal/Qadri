package com.example.qadri.ui.fragment.customerDetail.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.children
import com.example.qadri.R
import com.example.qadri.databinding.FragmentCheckInBinding
import com.example.qadri.databinding.FragmentRecoveryFormBinding
import com.example.qadri.ui.fragment.BaseDockFragment

class RecoveryForm : BaseDockFragment() {

    lateinit var bd : FragmentRecoveryFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentRecoveryFormBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.paymentStatus.isChecked = true
        bd.radioGrp.setOnCheckedChangeListener { radioGroup, i ->
            radioGroup.children.forEach {
                if(it is RadioButton){
                    if(it.isChecked){
                        if(it.id==bd.paymentStatus.id){
                            showCollectionForm()
                        }else{
                            showFollowUpForm()
                        }
                    }
                }
            }
        }
    }

    private fun showCollectionForm() {
        bd.collectionView.visibility=View.VISIBLE
        bd.followUpView.visibility=View.GONE
    }

    private fun showFollowUpForm() {
        bd.collectionView.visibility=View.GONE
        bd.followUpView.visibility=View.VISIBLE
    }


}