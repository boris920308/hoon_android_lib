package com.hoon.hoon_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoon.hoon_viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = resources.getString(R.string.hello_view_binding)
    }


}