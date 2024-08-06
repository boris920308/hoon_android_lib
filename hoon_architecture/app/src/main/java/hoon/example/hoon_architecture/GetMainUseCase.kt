package hoon.example.hoon_architecture

class GetMainUseCase(private val mainRepository: MainRepository) {
    fun execute(): List<MainModel> {
        return mainRepository.getUsers()
    }
}