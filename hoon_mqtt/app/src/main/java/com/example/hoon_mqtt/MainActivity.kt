package com.example.hoon_mqtt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hoon_mqtt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMain.text = "hello mqtt"
    }
}