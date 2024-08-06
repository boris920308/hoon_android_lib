package hoon.example.hoon_architecture

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import hoon.example.hoon_architecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainRepository = MainRepository()
        val getMainUseCase = GetMainUseCase(mainRepository)
        mainViewModel = MainViewModel(getMainUseCase)

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