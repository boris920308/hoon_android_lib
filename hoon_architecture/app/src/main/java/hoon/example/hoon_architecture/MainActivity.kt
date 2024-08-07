package hoon.example.hoon_architecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hoon.example.hoon_architecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainRepository = MainRepositoryImpl()
        val getMainUseCase = GetMainUseCase(mainRepository)
        val vmFactory = MainViewModelFactory(getMainUseCase)
        mainViewModel = ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)

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