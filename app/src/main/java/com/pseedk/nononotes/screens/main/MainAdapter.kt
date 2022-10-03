package com.pseedk.nononotes.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pseedk.nononotes.databinding.NoteItemBinding
import com.pseedk.nononotes.models.AppNote

class MainAdapter() : RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {

    class ItemViewHolder(val itemBinding: NoteItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback =
        object : DiffUtil.ItemCallback<AppNote>() {
            override fun areItemsTheSame(oldItem: AppNote, newItem: AppNote): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AppNote, newItem: AppNote): Boolean {
                return oldItem == newItem
            }
        }
     val differ = AsyncListDiffer(this, differCallback)


    override fun onViewAttachedToWindow(holder: ItemViewHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.click(differ.currentList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ItemViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.apply {
            itemNoteName.text = currentNote.name
            itemNoteText.text = currentNote.text
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun setList(list: List<AppNote>) {
    }

}
