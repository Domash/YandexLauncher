package com.domash.yandexlauncher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

//Fabric
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

//AppCentre
//import com.microsoft.appcenter.AppCenter
//import com.microsoft.appcenter.analytics.Analytics
//import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Fabric
        Fabric.with(this, Crashlytics())

        //AppCentre
        //AppCenter.start(
        //    application, BuildConfig.APPCENTER_KEY,
        //    Analytics::class.java, Crashes::class.java
        //)

        setContentView(R.layout.activity_main)
    }
}
