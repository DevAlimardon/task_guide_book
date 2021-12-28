package uz.infinityandro.taskguidebook.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.infinityandro.taskguidebook.data.UserNetwork

interface GuideBookUseCase {
    fun getData():Flow<Result<UserNetwork>>
}