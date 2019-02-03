package com.domash.yandexlauncher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

//Fabric
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Fabric
        Fabric.with(this, Crashlytics())

        setContentView(R.layout.activity_main)
    }
}
