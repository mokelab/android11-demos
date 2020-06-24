package com.mokelab.demo.android11

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.mokelab.demo.android11.adapter.SimpleListAdapter
import com.mokelab.demo.android11.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    var binding: MainFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MainFragmentBinding.bind(view)
        this.binding = binding

        val adapter = SimpleListAdapter(binder = { item, itemBinding ->
            itemBinding.text.text = item
        }, callbacks = callbacks, listener = listener)
        binding.recycler.adapter = adapter

        val items = resources.getStringArray(R.array.feature_names)
        adapter.submitList(items.toList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private val listener = object : SimpleListAdapter.ItemClickListener<String> {
        override fun onItemClicked(item: String, position: Int) {
            when (position) {
                0 -> findNavController().navigate(R.id.action_main_to_bubble)
                1 -> findNavController().navigate(R.id.action_main_to_mimegroup)
            }
        }
    }

    companion object {
        private val callbacks = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }
}