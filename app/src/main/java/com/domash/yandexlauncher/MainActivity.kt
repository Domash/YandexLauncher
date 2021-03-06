package com.domash.yandexlauncher

import android.app.LauncherActivity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

//Fabric
import com.crashlytics.android.Crashlytics
import com.domash.yandexlauncher.activities.LauncherPage.ItemListAdapter
import io.fabric.sdk.android.Fabric

//AppCentre
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.activity_launcher.*

class MainActivity : AppCompatActivity() {

    private lateinit var ListOfApps: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Fabric
        Fabric.with(this, Crashlytics())

        //AppCentre
        AppCenter.start(
            application, BuildConfig.APPCENTER_KEY,
            Analytics::class.java, Crashes::class.java
        )
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
