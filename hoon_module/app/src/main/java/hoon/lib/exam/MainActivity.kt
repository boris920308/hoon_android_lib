package hoon.lib.exam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hoon.lib.exam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = resources.getString(R.string.title_main)
    }
}