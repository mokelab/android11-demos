package com.mokelab.demo.android11.ime

import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsAnimation
import androidx.appcompat.widget.ActionBarOverlayLayout
import androidx.fragment.app.Fragment
import com.mokelab.demo.android11.ime.databinding.ImeFragmentBinding

class ImeFragment : Fragment(R.layout.ime_fragment) {
    private var _binding: ImeFragmentBinding? = null
    private val binding: ImeFragmentBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this._binding = ImeFragmentBinding.bind(view)

        binding.showButton.setOnClickListener {
            it.windowInsetsController?.show(WindowInsets.Type.ime())
        }

        binding.hideButton.setOnClickListener {
            it.windowInsetsController?.hide(WindowInsets.Type.ime())
        }

        binding.checkButton.setOnClickListener {
            checkImeStatus(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }

    private fun checkImeStatus(view: View) {
        val visible = view.rootWindowInsets.isVisible(WindowInsets.Type.ime())
        binding.statusText.text =
            getString(R.string.ime_status, if (visible) "Visible" else "Hidden")
    }

}
