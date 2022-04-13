package com.example.qadri.ui.fragment.customerDetail.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.FragmentCustomerNotesBinding
import com.example.qadri.mvvm.model.note.NoteModel
import com.example.qadri.ui.fragment.BaseDockFragment

class CustomerNotes : BaseDockFragment(), ClickListener {

    lateinit var bd : FragmentCustomerNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentCustomerNotesBinding.inflate(layoutInflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bd.recyclerView.adapter = AdapterRvNotes(this).apply {
            setList(arrayListOf<NoteModel>().apply {
                add(NoteModel("Title","Notes","13-4-2022"))
                add(NoteModel("Title","Notes","13-4-2022"))
                add(NoteModel("Title","Notes","13-4-2022"))
                add(NoteModel("Title","Notes","13-4-2022"))
                add(NoteModel("Title","Notes","13-4-2022"))
                add(NoteModel("Title","Notes","13-4-2022"))
            })
        }
    }

    override fun <T> onClick(data: T, createNested: Boolean) {
        val item = data as NoteModel
    }
}