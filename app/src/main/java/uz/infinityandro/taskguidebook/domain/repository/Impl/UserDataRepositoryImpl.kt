package uz.infinityandro.taskguidebook.domain.repository.Impl

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import uz.infinityandro.taskguidebook.app.App
import uz.infinityandro.taskguidebook.data.User
import uz.infinityandro.taskguidebook.domain.repository.UserDataRepository
import uz.infinityandro.taskguidebook.domain.room.UserDao
import uz.infinityandro.taskguidebook.domain.room.UserDatabase
import java.lang.reflect.Executable
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val context: Context):UserDataRepository {
    override fun getAllData(): Flow<Result<List<User>>> = flow {
        try {
            var list=ArrayList<User>()
                val dao = UserDatabase.getInstance(App.instance).getDao()
                list.addAll(dao.getAllUser())
            if (!list.isNullOrEmpty()){
                emit(Result.success(list))
            }else{
                emit(Result.failure(Throwable("No Data")))
            }
        }catch (e:Exception){

        }
    }.flowOn(Dispatchers.IO)
}