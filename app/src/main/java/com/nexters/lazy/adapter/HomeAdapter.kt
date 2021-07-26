package com.nexters.lazy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.lazy.R

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.SampleViewHolder>() {

    private val items = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SampleViewHolder(parent)

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        //..
    }

    override fun getItemCount() = items.size

    fun notifySample() {
        items.clear()
        (1..3).forEach {
            items.add(it)
        }
        notifyDataSetChanged()
    }

    class SampleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_home_habit, parent, false),
    )
}