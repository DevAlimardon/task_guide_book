package uz.infinityandro.taskguidebook.domain.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.infinityandro.taskguidebook.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                instance = DatabaseSave(context)
            }
            return instance!!
        }

        private fun DatabaseSave(context: Context): UserDatabase? =
            Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"my_aliaaasas")
                .build()
    }
}