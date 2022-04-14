package com.example.qadri.ui.fragment.customerDetail.actions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qadri.dagger.base.ClickListener
import com.example.qadri.databinding.ItemNoteBinding
import com.example.qadri.mvvm.model.bookedOrder.BookedOrderModel
import com.example.qadri.mvvm.model.note.NoteModel

class AdapterRvNotes(val listener:ClickListener) : RecyclerView.Adapter<AdapterRvNotes.NoteHolder>() {

    lateinit var context: Context
    val notesList = arrayListOf<NoteModel>()

    inner class NoteHolder(val bd: ItemNoteBinding) :
        RecyclerView.ViewHolder(bd.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val bd =
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(bd)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val item = notesList[position]
        holder.bd.title.text = item.title
        holder.bd.note.text = item.note
        holder.bd.date.text = item.date
        holder.bd.root.setOnClickListener {
            listener.onClick(item)
        }
    }

    fun setList(list: ArrayList<NoteModel>){
        notesList.clear()
        notesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}