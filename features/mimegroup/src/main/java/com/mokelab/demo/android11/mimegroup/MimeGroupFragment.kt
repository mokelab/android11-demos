package com.mokelab.demo.android11.mimegroup

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mokelab.demo.android11.mimegroup.databinding.MimeGroupFragmentBinding

class MimeGroupFragment : Fragment(R.layout.mime_group_fragment) {
    var binding: MimeGroupFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MimeGroupFragmentBinding.bind(view)
        this.binding = binding

        binding.addButton.setOnClickListener {
            val type = binding.addTypeEdit.text.toString()
            addMimeType(type)
        }

        binding.viewButton.setOnClickListener {
            val type = binding.viewTypeEdit.text.toString()
            viewWithType(type)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun addMimeType(type: String) {
        val pm = requireContext().packageManager
        val types = setOf(type)
        pm.setMimeGroup(MIME_GROUP, types)

        this.binding?.addTypeEdit?.setText(R.string.empty)
        Snackbar.make(requireView(), R.string.done_add, Snackbar.LENGTH_SHORT).show()
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

    companion object {
        private const val MIME_GROUP = "com.mokelab:demoFeature"
    }

}