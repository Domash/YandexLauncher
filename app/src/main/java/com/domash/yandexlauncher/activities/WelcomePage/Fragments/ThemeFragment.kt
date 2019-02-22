package com.domash.yandexlauncher.activities.WelcomePage.Fragments


import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton

import com.domash.yandexlauncher.R
import kotlinx.android.synthetic.main.fragment_model_type.*
import android.widget.RadioGroup
import android.preference.PreferenceManager
import com.domash.yandexlauncher.Constants

class ThemeFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view: View = inflater.inflate(R.layout.fragment_theme, container, false);

        val rbDark: RadioButton =  view.findViewById(R.id.radio_button_theme_dark)
        val rbLight: RadioButton = view.findViewById(R.id.radio_button_theme_light)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        val themeDark: Boolean = sharedPreferences
            .getBoolean(Constants.pref_key_theme_dark, false)

        if (themeDark) {
            rbDark.isChecked = true
            rbLight.isChecked = false
        } else {
            rbDark.isChecked = false
            rbLight.isChecked = true
        }

        val rgTheme: RadioGroup = view.findViewById(R.id.radio_group_theme)

        rgTheme.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_button_theme_light -> {
                    if (themeDark) {
                        sharedPreferences.edit()
                            .putBoolean(Constants.pref_key_theme_dark, false)
                            .apply()
                    }
                }
                R.id.radio_button_theme_dark -> {
                    if (!themeDark) {
                        sharedPreferences.edit()
                            .putBoolean(Constants.pref_key_theme_dark, true)
                            .apply()
                    }
                }
            }
        }


        return view
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(key == Constants.pref_key_theme_dark) activity?.recreate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(context)
                         .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(context)
                         .unregisterOnSharedPreferenceChangeListener(this)
    }

}
