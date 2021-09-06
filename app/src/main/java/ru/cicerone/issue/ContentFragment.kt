package ru.cicerone.issue

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class ContentFragment : Fragment(R.layout.fragment_content) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colorName = arguments?.getString(NAME) ?: error("Color name argument not found")
        val backgroundColorResId = arguments?.getInt(COLOR) ?: error("Background color resource argument not found")
        view.setBackgroundColor(requireContext().getColor(backgroundColorResId))
        view.findViewById<TextView>(R.id.title).text = "${ContentFragment::class.java.simpleName}($colorName)"
    }

    companion object {
        private const val NAME = "name"
        private const val COLOR = "color"

        fun instance(
            colorName: String,
            @ColorRes backgroundColor: Int
        ) = ContentFragment().apply {
            arguments = bundleOf(
                NAME to colorName,
                COLOR to backgroundColor
            )
        }
    }
}