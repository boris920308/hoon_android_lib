package com.hoon.hoon_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoon.hoon_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var items: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = MyRecyclerViewAdapter(getSampleItems())
    }

    private fun getSampleItems(): ArrayList<String>{

        val items = arrayListOf<String>()

        for (i in 0 .. 50) {
            items.add("item no.$i")
        }

        return items
    }
}