package ru.cicerone.issue

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.FragmentScreen

class TabFragment : Fragment(R.layout.fragment_tab) {

    private val cicerone = Cicerone.create()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(ID) ?: error("Id resource not found")

        val colorResId: Int
        val childColorResId: Int
        val colorName: String

        when (id) {
            R.id.red -> {
                colorResId = R.color.red_800
                childColorResId = R.color.red_400
                colorName = getString(R.string.red)
            }
            R.id.green -> {
                colorResId = R.color.green_800
                childColorResId = R.color.green_400
                colorName = getString(R.string.green)
            }
            R.id.orange -> {
                colorResId = R.color.orange_800
                childColorResId = R.color.orange_400
                colorName = getString(R.string.orange)
            }
            R.id.blue -> {
                colorResId = R.color.blue_800
                childColorResId = R.color.blue_400
                colorName = getString(R.string.blue)
            }
            else -> error("Unexpected menu item id")
        }

        view.setBackgroundColor(requireContext().getColor(colorResId))
        view.findViewById<TextView>(R.id.title).text = "${TabFragment::class.java.simpleName}($colorName)"

        cicerone.router.replaceScreen(FragmentScreen {
            ContentFragment.instance(colorName, childColorResId)
        })
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(Navigator(R.id.content_container, this))
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    companion object {
        private const val ID = "id"

        fun instance(@IdRes menuItemId: Int) = TabFragment().apply {
            arguments = bundleOf(ID to menuItemId)
        }
    }
}