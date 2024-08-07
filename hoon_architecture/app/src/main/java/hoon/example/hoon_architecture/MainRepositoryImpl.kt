package hoon.example.hoon_architecture

class MainRepositoryImpl : MainRepositoryInterface {
    private val users = listOf(
        MainModel(1, "Alice"),
        MainModel(2, "Bob"),
        MainModel(3, "Charlie"),
    )

    override fun getUsers(): List<MainModel> {
        return users
    }
}