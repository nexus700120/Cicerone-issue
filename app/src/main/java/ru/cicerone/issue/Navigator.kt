package ru.cicerone.issue

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.AppNavigator

class Navigator(
    containerId: Int,
    fragment: Fragment
) : AppNavigator(fragment.requireActivity(), containerId, fragment.childFragmentManager)