package com.hoon.hoon_viewpager.with_item

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoon.hoon_viewpager.databinding.ActivityWithRecyclerBinding

class WithItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWithRecyclerBinding
    private lateinit var adapter: WithItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WithItemAdapter()
        binding.vp.adapter = adapter
        adapter.submitList(listOf("1", "2", "3", "4"))

    }
}