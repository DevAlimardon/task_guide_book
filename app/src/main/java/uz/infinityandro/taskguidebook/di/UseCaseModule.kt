package uz.infinityandro.taskguidebook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.infinityandro.taskguidebook.domain.usecase.GuideBookUseCase
import uz.infinityandro.taskguidebook.domain.usecase.Impl.GuideBookUseCaseImpl
import uz.infinityandro.taskguidebook.domain.usecase.Impl.UserDataUseCaeImpl
import uz.infinityandro.taskguidebook.domain.usecase.UserDataUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun getUsecase(impl:GuideBookUseCaseImpl):GuideBookUseCase

    @Binds
    @Singleton
    abstract fun getDataUseCase(impl:UserDataUseCaeImpl):UserDataUseCase

}