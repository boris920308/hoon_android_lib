package hoon.example.hoon_architecture

import javax.inject.Inject

class GetMainUseCase @Inject constructor(
    private val mainRepository: MainRepositoryInterface
) {
//    fun execute(): List<MainModel> {
//        return mainRepository.getUsers()
//    }

    operator fun invoke(): List<MainModel> {
        return mainRepository.getUsers()
    }
}