package com.domash.yandexlauncher.activities.LauncherPage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domash.yandexlauncher.AppInformation
import com.domash.yandexlauncher.R

class ItemListAdapter(
    private val context: Context,
    private val appList: List<AppInformation>
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun getItemCount(): Int = appList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(appList[position])
        holder.setOnClickListener(View.OnClickListener {
            context.startActivity(appList[position].lIntent)
        })
    }

}