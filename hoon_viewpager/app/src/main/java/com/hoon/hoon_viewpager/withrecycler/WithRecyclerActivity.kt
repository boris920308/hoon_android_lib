package com.hoon.hoon_viewpager.withrecycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoon.hoon_viewpager.databinding.ActivityWithRecyclerBinding

class WithRecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWithRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}