package com.hoon.hoon_viewpager.with_item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hoon.hoon_viewpager.databinding.ItemHelloBinding

class WithItemAdapter : ListAdapter<String, RecyclerView.ViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WithItemViewHolder(
            ItemHelloBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WithItemViewHolder).onBind(getItem(position))

    }

    inner class WithItemViewHolder(private val binding: ItemHelloBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: String) {
                binding.tv.text = "page = $item"
        }
    }

}

object DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}