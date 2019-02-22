package com.domash.yandexlauncher.activities.WelcomePage

import android.app.LauncherActivity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.app.AppCompatActivity
import com.domash.yandexlauncher.R

import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.welcome_page.*
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import com.domash.yandexlauncher.Constants
import com.domash.yandexlauncher.MainActivity
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer


class WelcomePage : AppCompatActivity() {


    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)


        mViewPager = welcome_page_view_pager
        mViewPager.adapter = WelcomePageAdapter(supportFragmentManager)
        mViewPager.setPageTransformer(false, CardFlipPageTransformer())

        next_page_button.setOnClickListener {
            val currFragmentNumber: Int = mViewPager.currentItem
            if(currFragmentNumber < 3) {
                mViewPager.currentItem = currFragmentNumber + 1
            } else {
                //Need to save preferences in next time

                val nextInt = Intent(this, MainActivity::class.java).apply{
                    putExtra(EXTRA_MESSAGE, "")
                }
                startActivity(nextInt)


            }
        }

    }

    override fun getTheme(): Resources.Theme {
        val theme = super.getTheme()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val dark = sharedPreferences.getBoolean(Constants.pref_key_theme_dark, false)
        if (dark)
            theme.applyStyle(R.style.AppThemeDark, true)
         else
            theme.applyStyle(R.style.AppThemeLight, true)

        return theme
    }

}