package com.domash.yandexlauncher

import android.content.Intent
import android.graphics.drawable.Drawable

data class AppInformation(
    val name: String,
    val icon: Drawable,
    val lIntent: Intent
)