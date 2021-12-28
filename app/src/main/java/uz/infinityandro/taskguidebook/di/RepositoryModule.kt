package uz.infinityandro.taskguidebook.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.infinityandro.taskguidebook.domain.repository.GuideBookRepository
import uz.infinityandro.taskguidebook.domain.repository.Impl.GuideBookRepositoryImpl
import uz.infinityandro.taskguidebook.domain.repository.Impl.UserDataRepositoryImpl
import uz.infinityandro.taskguidebook.domain.repository.UserDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun getRepository(repositoryImpl: GuideBookRepositoryImpl):GuideBookRepository

    @Binds
    @Singleton
    abstract fun getDataRepos(userDataRepositoryImpl: UserDataRepositoryImpl):UserDataRepository
}