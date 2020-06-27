package com.mokelab.demo.android11.ime

import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.mokelab.demo.android11.ime.databinding.WindowInsetsFragmentBinding

class WindowInsetsFragment : Fragment(R.layout.window_insets_fragment) {

    private var _binding: WindowInsetsFragmentBinding? = null
    private val binding: WindowInsetsFragmentBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this._binding = WindowInsetsFragmentBinding.bind(view)

        binding.showButton.setOnClickListener {
            it.windowInsetsController?.show(WindowInsets.Type.ime())
        }
        binding.hideButton.setOnClickListener {
            it.windowInsetsController?.hide(WindowInsets.Type.ime())
        }

        binding.root.setWindowInsetsAnimationCallback(object : WindowInsetsAnimation.Callback(
            DISPATCH_MODE_CONTINUE_ON_SUBTREE
        ) {
            var visible = false

            override fun onStart(
                animation: WindowInsetsAnimation,
                bounds: WindowInsetsAnimation.Bounds
            ): WindowInsetsAnimation.Bounds {
                this.visible = binding.root.rootWindowInsets.isVisible(WindowInsets.Type.ime())
                return super.onStart(animation, bounds)
            }

            override fun onProgress(
                insets: WindowInsets,
                runningAnimations: MutableList<WindowInsetsAnimation>
            ): WindowInsets {
                val params = binding.image.layoutParams as ConstraintLayout.LayoutParams
                params.horizontalBias =
                    if (this.visible) runningAnimations[0].interpolatedFraction else 1 - runningAnimations[0].interpolatedFraction
                binding.image.layoutParams = params
                binding.image.requestLayout()

                println("${runningAnimations[0].interpolatedFraction}")
                println(runningAnimations.size)
                return insets
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}