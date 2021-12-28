package uz.infinityandro.taskguidebook.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.infinityandro.taskguidebook.data.User

interface UserDataRepository {
    fun getAllData():Flow<Result<List<User>>>
}