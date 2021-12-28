package uz.infinityandro.taskguidebook.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.infinityandro.taskguidebook.data.UserNetwork

interface GuideBookRepository {
    fun getData():Flow<Result<UserNetwork>>
}