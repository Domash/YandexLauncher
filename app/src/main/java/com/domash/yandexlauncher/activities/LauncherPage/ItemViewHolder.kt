package com.domash.yandexlauncher.activities.LauncherPage

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.domash.yandexlauncher.AppInformation
import com.domash.yandexlauncher.R

class ItemViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.item_name)
    private val icon: ImageView = itemView.findViewById(R.id.item_icon)

    fun bind(app: AppInformation) {
        name.text = app.name
        icon.setImageDrawable(app.icon)
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }

}