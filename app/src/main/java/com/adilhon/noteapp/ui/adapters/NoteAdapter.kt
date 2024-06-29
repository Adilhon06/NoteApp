package com.adilhon.noteapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adilhon.noteapp.R
import com.adilhon.noteapp.databinding.NoteItemBinding
import com.adilhon.noteapp.ui.fragments.models.NoteModel

class NoteAdapter(private val context: Context, val onClick: (note: NoteModel) -> Unit) :
    ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {

    private var noteList = listOf<NoteModel>()

    inner class ViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.tvNote.text = item.title
            binding.tvTitleNote.text = item.description
            binding.tvDateTime.text = item.time

            itemView.setOnClickListener {
                onClick(item)
            }

            if (item.containerColor == "white") {
                binding.container.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            } else if (item.containerColor == "red") {
                binding.container.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
            } else if (item.containerColor == "gray") {
                binding.container.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.background_gray
                    )
                )
            }else if (item.containerColor == "")  binding.container.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.background_gray
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.title == newItem.title
        }
    }
}