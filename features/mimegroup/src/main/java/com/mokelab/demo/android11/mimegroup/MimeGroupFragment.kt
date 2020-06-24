package com.mokelab.demo.android11.mimegroup

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mokelab.demo.android11.adapter.SimpleListAdapter
import com.mokelab.demo.android11.adapter.SimpleStringListAdapter
import com.mokelab.demo.android11.mimegroup.databinding.MimeGroupFragmentBinding

class MimeGroupFragment : Fragment(R.layout.mime_group_fragment) {
    private var binding: MimeGroupFragmentBinding? = null
    private var adapter: SimpleStringListAdapter? = null

    private val vm: MimeGroupViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MimeGroupFragmentBinding.bind(view)
        this.binding = binding

        binding.addButton.setOnClickListener {
            val type = binding.addTypeEdit.text.toString()
            vm.addType(type)
            binding.addTypeEdit.setText(R.string.empty)
        }

        binding.submitButton.setOnClickListener {
            vm.submitTypes()
            Snackbar.make(requireView(), R.string.done_submit, Snackbar.LENGTH_SHORT).show()
        }

        binding.viewButton.setOnClickListener {
            val type = binding.viewTypeEdit.text.toString()
            viewWithType(type)
        }

        this.adapter =
            SimpleStringListAdapter(object : SimpleListAdapter.ItemClickListener<String> {
                override fun onItemClicked(item: String, position: Int) {
                    vm.removeType(item)
                }
            })
        binding.recycler.adapter = this.adapter

        this.vm.mimeTypes.observe(viewLifecycleOwner, {
            this.adapter?.submitList(it.toList().sorted())
        })

        if (savedInstanceState == null) {
            vm.loadTypes()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun viewWithType(type: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.type = type

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Snackbar.make(requireView(), R.string.err_activity_not_found, Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}