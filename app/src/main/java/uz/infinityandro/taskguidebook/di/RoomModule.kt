package uz.infinityandro.taskguidebook.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.infinityandro.taskguidebook.domain.room.UserDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

//    @Singleton
//    @Provides
//    fun provideDatabase(
//        @ApplicationContext app: Context
//    ) = Room.databaseBuilder(
//        app,
//        UserDatabase::class.java, "my_list"
//    )
//        .build()
//
//    @Singleton
//    @Provides
//    fun provideDao(db:UserDatabase)=db.getDao()
}