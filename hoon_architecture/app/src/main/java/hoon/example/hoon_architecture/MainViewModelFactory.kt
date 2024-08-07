package hoon.example.hoon_architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val getMainUseCase: GetMainUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(getMainUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}