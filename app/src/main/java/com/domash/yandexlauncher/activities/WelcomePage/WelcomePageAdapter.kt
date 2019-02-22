package com.domash.yandexlauncher.activities.WelcomePage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.domash.yandexlauncher.activities.WelcomePage.Fragments.AppInfoFragment
import com.domash.yandexlauncher.activities.WelcomePage.Fragments.ModelTypeFragment
import com.domash.yandexlauncher.activities.WelcomePage.Fragments.StartFragment
import com.domash.yandexlauncher.activities.WelcomePage.Fragments.ThemeFragment

class WelcomePageAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return StartFragment()
            }
            1 -> {
                return AppInfoFragment()
            }
            2 -> {
                return ThemeFragment()
            }
            3 -> {
                return ModelTypeFragment()
            }
        }
        return null
    }
}