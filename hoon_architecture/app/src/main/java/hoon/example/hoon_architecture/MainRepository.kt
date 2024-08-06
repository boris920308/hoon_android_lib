package hoon.example.hoon_architecture

class MainRepository {
    private val users = listOf(
        MainModel(1, "Alice"),
        MainModel(2, "Bob"),
        MainModel(3, "Charlie"),
    )

    fun getUsers(): List<MainModel> {
        return users
    }
}