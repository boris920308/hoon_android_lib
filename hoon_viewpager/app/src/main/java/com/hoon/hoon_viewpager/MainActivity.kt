package com.hoon.hoon_viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoon.hoon_viewpager.databinding.ActivityMainBinding
import com.hoon.hoon_viewpager.with_item.WithItemActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnGoRecycler.setOnClickListener {
            val intent =  Intent(this, WithItemActivity::class.java)
            startActivity(intent)
        }
    }
}