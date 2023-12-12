package com.hoon.hoon_viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoon.hoon_viewpager.databinding.ActivityMainBinding
import com.hoon.hoon_viewpager.withrecycler.WithRecyclerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnGoRecycler.setOnClickListener {
            val intent =  Intent(this, WithRecyclerActivity::class.java)
            startActivity(intent)
        }
    }
}