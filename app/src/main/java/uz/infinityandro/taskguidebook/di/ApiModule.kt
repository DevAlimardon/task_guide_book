package uz.infinityandro.taskguidebook.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.infinityandro.taskguidebook.data.api.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule{

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit):ApiService=retrofit.create(ApiService::class.java)
}