package hoon.example.hoon_architecture

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainUseCaseModule {

    @Provides
    @Singleton
    fun provideGetMainUseCaseModule(mainRepository: MainRepositoryInterface): GetMainUseCase {
        return GetMainUseCase(mainRepository)
    }
}