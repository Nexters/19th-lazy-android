package com.nexters.lazy.habitform

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.nexters.lazy.toPxInt

class HabitIconAdapter(
    private val list: List<HabitIcon>,
    private val onClick: (item: HabitIcon) -> Any
) :
    RecyclerView.Adapter<HabitIconAdapter.HabitIconViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitIconViewHolder {
        val view = ImageView(parent.context)
        view.layoutParams = RecyclerView.LayoutParams(
            42.toPxInt(parent.context),
            42.toPxInt(parent.context)
        )
        return HabitIconViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitIconViewHolder, position: Int) {
        val item = list[position]
        holder.view.setBackgroundResource(item.res)
        val margin = 6.toPxInt(holder.view.context)
        when (position) {
            list.size - 1 -> {
                holder.view.updateLayoutParams<RecyclerView.LayoutParams> {
                    setMargins(0, 0, 0, 0)
                }
            }
            else -> {
                holder.view.updateLayoutParams<RecyclerView.LayoutParams> {
                    setMargins(0, 0, margin, 0)
                }
            }
        }
        if (item.isClicked) {
            holder.view.alpha = 1f
        } else {
            holder.view.alpha = 0.3f
        }
        holder.view.setOnClickListener {
            item.isClicked = true
            list.filter { it.id != position + 1 }.map { it.isClicked = false }
            onClick.invoke(item)
            notifyDataSetChanged()
        }
    }


    class HabitIconViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }
}