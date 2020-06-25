package com.mokelab.demo.android11.exitreason

import android.app.ApplicationExitInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mokelab.demo.android11.exitreason.databinding.ExitReasonFragmentBinding
import java.lang.RuntimeException
import kotlin.system.exitProcess

class ExitReasonFragment : Fragment(R.layout.exit_reason_fragment) {
    private var binding: ExitReasonFragmentBinding? = null

    private val vm: ExitReasonViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ExitReasonFragmentBinding.bind(view)
        this.binding = binding

        binding.anrButton.setOnClickListener {
            performANR()
        }
        binding.crashButton.setOnClickListener {
            performCrash()
        }
        binding.exitButton.setOnClickListener {
            performExit()
        }

        val adapter = ExitReasonAdapter(object : ExitReasonAdapter.OnItemClickListener {
            override fun onItemClicked(info: ApplicationExitInfo) {
                val action = ExitReasonFragmentDirections.actionExitListToDetail(info)
                findNavController().navigate(action)
            }
        })
        binding.recycler.adapter = adapter

        this.vm.reasons.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun performANR() {
        // wait 30 seconds
        Thread.sleep(30 * 1000)
    }

    private fun performCrash() {
        throw RuntimeException("CRASH!!!")
    }

    private fun performExit() {
        exitProcess(1)
    }

}