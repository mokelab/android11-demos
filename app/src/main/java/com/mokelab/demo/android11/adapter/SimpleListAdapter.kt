package com.mokelab.demo.android11.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mokelab.demo.android11.databinding.SimpleListItemBinding

class SimpleListAdapter<T>(
    private val binder: (T, SimpleListItemBinding) -> Unit,
    private val listener: SimpleListAdapter.ItemClickListener<T>,
    callbacks: DiffUtil.ItemCallback<T>
) :
    ListAdapter<T, SimpleListAdapter.ViewHolder>(callbacks) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SimpleListItemBinding.inflate(inflater, parent, false)
        val viewHolder = ViewHolder(binding)

        binding.root.setOnClickListener {
            val position = viewHolder.adapterPosition
            val item = getItem(position)
            listener.onItemClicked(item, position)
        }

        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        binder(item, viewHolder.binding)
    }

    class ViewHolder(val binding: SimpleListItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface ItemClickListener<T> {
        fun onItemClicked(item: T, position: Int)
    }
}