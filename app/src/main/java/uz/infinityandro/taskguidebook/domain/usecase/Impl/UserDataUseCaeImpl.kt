package uz.infinityandro.taskguidebook.domain.usecase.Impl

import kotlinx.coroutines.flow.Flow
import uz.infinityandro.taskguidebook.data.User
import uz.infinityandro.taskguidebook.domain.repository.UserDataRepository
import uz.infinityandro.taskguidebook.domain.usecase.UserDataUseCase
import javax.inject.Inject

class UserDataUseCaeImpl @Inject constructor(private val repository: UserDataRepository):UserDataUseCase {
    override fun getData(): Flow<Result<List<User>>> = repository.getAllData()
}