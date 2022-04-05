package com.example.qadri.ui.fragment.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GraphFragmentAdapter(fm:FragmentManager):FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
){

    private val fragList = arrayListOf<Fragment>()

    override fun getCount(): Int {
        return 2
    }

    fun addFragment(frag: Fragment){
        fragList.add(frag)
    }

    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }

}