package ru.cicerone.issue

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.bottomnavigation.BottomNavigationView

class RootFragment : Fragment(R.layout.fragment_root) {

    private val cicerone = Cicerone.create()

    private lateinit var bottomMenu: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomMenu = view.findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottomMenu.setOnItemSelectedListener {
            onTabSelected(it.itemId)
            true
        }
        onTabSelected(R.id.red)
    }

    private fun onTabSelected(@IdRes menuItemId: Int) {
        val color = when (menuItemId) {
            R.id.red -> R.color.red_400
            R.id.green -> R.color.green_400
            R.id.orange -> R.color.orange_400
            R.id.blue -> R.color.blue_400
            else -> Color.BLACK
        }
        val colorStateList = createColorStateList(color)
        bottomMenu.itemIconTintList = colorStateList
        bottomMenu.itemTextColor = colorStateList

        cicerone.router.replaceScreen(FragmentScreen { TabFragment.instance(menuItemId) })
    }

    private fun createColorStateList(@ColorRes res: Int): ColorStateList =
        ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                requireContext().getColor(res),
                Color.LTGRAY
            )
        )

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(
            CustomAppNavigator(
                requireActivity(),
                R.id.tab_container,
                childFragmentManager
            )
        )
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }
}