package com.mokelab.demo.android11.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mokelab.demo.android11.adapter.databinding.SimpleListItemBinding

class SimpleStringListAdapter(listener: ItemClickListener<String>) :
    SimpleListAdapter<String>(binder, listener, callbacks) {
    companion object {
        private val binder = { item: String, binding: SimpleListItemBinding ->
            binding.text.text = item
        }

        private val callbacks = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        }
    }
}