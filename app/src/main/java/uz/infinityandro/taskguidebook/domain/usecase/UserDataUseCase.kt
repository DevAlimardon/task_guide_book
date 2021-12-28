package uz.infinityandro.taskguidebook.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.infinityandro.taskguidebook.data.User

interface UserDataUseCase {
    fun getData():Flow<Result<List<User>>>
}