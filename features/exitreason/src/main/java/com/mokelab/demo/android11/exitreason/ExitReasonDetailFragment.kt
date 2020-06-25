package com.mokelab.demo.android11.exitreason

import android.app.ApplicationExitInfo
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mokelab.demo.android11.exitreason.databinding.ExitReasonDetailFragmentBinding

class ExitReasonDetailFragment : Fragment(R.layout.exit_reason_detail_fragment) {
    private var binding: ExitReasonDetailFragmentBinding? = null

    val args: ExitReasonDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ExitReasonDetailFragmentBinding.bind(view)
        this.binding = binding

        bind(binding, args.info)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun bind(binding: ExitReasonDetailFragmentBinding, info: ApplicationExitInfo) {
        binding.apply {
            reasonText.text = toReasonLabel(info.reason)
            descriptionText.text = info.description
            timestampText.text = getString(
                R.string.detail_timestamp,
                DateFormat.format("yyyy-MM-dd hh:mm:ss", info.timestamp)
            )
            statusText.text = getString(R.string.detail_status, info.status)
            pidText.text = getString(R.string.detail_pid, info.pid)
            processNameText.text = getString(R.string.detail_process_name, info.processName)
            importanceText.text = getString(R.string.detail_importance, info.importance)
            pssText.text = getString(R.string.detail_pss, info.pss)
            rssText.text = getString(R.string.detail_rss, info.rss)
            packageUidText.text = getString(R.string.detail_package_uid, info.packageUid)
            definingUidText.text = getString(R.string.detail_defining_uid, info.definingUid)
            realUidText.text = getString(R.string.detail_real_uid, info.realUid)
        }
    }

}