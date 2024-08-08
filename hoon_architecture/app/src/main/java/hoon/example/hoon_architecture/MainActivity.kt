package hoon.example.hoon_architecture

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hoon.example.hoon_architecture.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.users.observe(this, Observer { users ->
            showData(users)
        })

        binding.btnGetData.setOnClickListener {
            mainViewModel.loadUsers()
        }
    }

    private fun showData(users: List<MainModel>) {
        binding.tvResult.text = users.toString()
    }
}