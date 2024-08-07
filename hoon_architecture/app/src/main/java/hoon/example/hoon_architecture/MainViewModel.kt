package hoon.example.hoon_architecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val getMainUseCase: GetMainUseCase) : ViewModel() {

    private val _users = MutableLiveData<List<MainModel>>()
    val users: LiveData<List<MainModel>> get() = _users

    fun loadUsers() {
        val userList = getMainUseCase.execute()
        _users.value = userList
    }
}