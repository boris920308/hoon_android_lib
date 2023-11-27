package com.hoon.hoon_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(private val items: ArrayList<String>): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sample, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView

        init {
            tvTitle = view.findViewById(R.id.tv_title)
        }

        fun onBind(item: String) {
            tvTitle.text = item
        }
    }
}