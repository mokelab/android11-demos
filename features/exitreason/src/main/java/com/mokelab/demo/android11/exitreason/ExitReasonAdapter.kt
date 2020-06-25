package com.mokelab.demo.android11.exitreason

import android.app.ApplicationExitInfo
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mokelab.demo.android11.exitreason.databinding.ExitReasonItemBinding

class ExitReasonAdapter(private val listener: OnItemClickListener) :
    ListAdapter<ApplicationExitInfo, ExitReasonAdapter.ViewHolder>(callbacks) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExitReasonItemBinding.inflate(inflater, parent, false)

        val holder = ViewHolder(binding)
        binding.root.setOnClickListener {
            val position = holder.adapterPosition
            val info = getItem(position)
            listener.onItemClicked(info)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = getItem(position)
        holder.bindTo(info)
    }

    class ViewHolder(private val binding: ExitReasonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(info: ApplicationExitInfo) {
            binding.reasonText.text = toReasonLabel(info.reason)
            binding.datetimeText.text = DateFormat.format("yyyy-MM-dd hh:mm:ss", info.timestamp)
        }

    }

    companion object {
        private val callbacks = object : DiffUtil.ItemCallback<ApplicationExitInfo>() {
            override fun areItemsTheSame(
                oldItem: ApplicationExitInfo,
                newItem: ApplicationExitInfo
            ) = oldItem.timestamp == newItem.timestamp

            override fun areContentsTheSame(
                oldItem: ApplicationExitInfo,
                newItem: ApplicationExitInfo
            ) = oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(info: ApplicationExitInfo)
    }
}