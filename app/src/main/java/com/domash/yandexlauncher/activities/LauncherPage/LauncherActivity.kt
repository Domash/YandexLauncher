package com.domash.yandexlauncher.activities.LauncherPage

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.domash.yandexlauncher.AppInformation
import com.domash.yandexlauncher.R

import kotlinx.android.synthetic.main.activity_launcher.*
import com.domash.yandexlauncher.MainActivity



class LauncherActivity : AppCompatActivity() {


    private lateinit var ListOfApps: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        setSupportActionBar(toolbar)

        val loadedApps = getAppsList()

        ListOfApps = findViewById(R.id.apps_list)
        ListOfApps.layoutManager = LinearLayoutManager(this)

        ListOfApps.adapter = ItemListAdapter(this, loadedApps)

    }

    private fun getAppsList(): List<AppInformation> {
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val resolveAppsList: List<ResolveInfo> =
            packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA)
        val appsList = ArrayList<AppInformation>()

        for(item in resolveAppsList) {
            if(item.activityInfo.packageName != packageName)
                appsList.add(getInfoAboutApp(item))
        }
        return appsList
    }

    private fun getInfoAboutApp(info: ResolveInfo): AppInformation {
        val icon = info.loadIcon(packageManager)
        val label = info.loadLabel(packageManager).toString()
        val lIntent = Intent(Intent.ACTION_MAIN)
        lIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        lIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
        lIntent.component =
                ComponentName(info.activityInfo.applicationInfo.packageName, info.activityInfo.name)
        return AppInformation(label, icon, lIntent)
    }

}
