package uz.infinityandro.taskguidebook.domain.usecase.Impl

import kotlinx.coroutines.flow.Flow
import uz.infinityandro.taskguidebook.data.UserNetwork
import uz.infinityandro.taskguidebook.domain.repository.GuideBookRepository
import uz.infinityandro.taskguidebook.domain.usecase.GuideBookUseCase
import javax.inject.Inject

class GuideBookUseCaseImpl @Inject constructor(private val repository: GuideBookRepository):GuideBookUseCase {
    override fun getData(): Flow<Result<UserNetwork>> = repository.getData()
}