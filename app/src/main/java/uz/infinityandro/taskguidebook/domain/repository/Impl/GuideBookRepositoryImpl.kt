package uz.infinityandro.taskguidebook.domain.repository.Impl

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.infinityandro.taskguidebook.data.DataItem
import uz.infinityandro.taskguidebook.data.User
import uz.infinityandro.taskguidebook.data.UserNetwork
import uz.infinityandro.taskguidebook.data.api.ApiService
import uz.infinityandro.taskguidebook.domain.repository.GuideBookRepository
import uz.infinityandro.taskguidebook.domain.room.UserDatabase
import javax.inject.Inject

class GuideBookRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val context: Context
) :
    GuideBookRepository {
    private val dao = UserDatabase.getInstance(context).getDao()
    override fun getData(): Flow<Result<UserNetwork>> = flow {
        try {
            val response = api.getAllData()
            if (response.isSuccessful) {
                save(response.body()?.data)
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        } catch (e: Exception) {
            emit(Result.failure(Throwable("Server Failed")))
        }
    }

    private fun save(data: List<DataItem>?) {
        val list2 = ArrayList<User>()
        data?.forEach {
            list2.add(
                User(
                    0,
                    it.venue.toString(),
                    it.endDate,
                    it.name,
                    it.icon,
                    it.loginRequired,
                    it.objType,
                    it.url,
                    it.startDate
                )
            )
        }
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(list2)
        }
    }
}

