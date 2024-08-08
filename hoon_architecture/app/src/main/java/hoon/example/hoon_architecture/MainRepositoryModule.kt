package hoon.example.hoon_architecture

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainRepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository() : MainRepositoryInterface {
        return MainRepositoryImpl()
    }
}