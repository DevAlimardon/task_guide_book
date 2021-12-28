package uz.infinityandro.taskguidebook.domain.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.infinityandro.taskguidebook.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<User>)

    @Query("SELECT * FROM USER")
    fun getAllUser():List<User>

}