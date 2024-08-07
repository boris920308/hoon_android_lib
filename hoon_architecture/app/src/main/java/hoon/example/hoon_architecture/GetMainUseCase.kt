package hoon.example.hoon_architecture

class GetMainUseCase(private val mainRepository: MainRepositoryImpl) {
    fun execute(): List<MainModel> {
        return mainRepository.getUsers()
    }
}