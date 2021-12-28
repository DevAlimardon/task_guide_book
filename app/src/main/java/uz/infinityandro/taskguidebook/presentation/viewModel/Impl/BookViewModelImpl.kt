package uz.infinityandro.taskguidebook.presentation.viewModel.Impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.infinityandro.taskguidebook.data.DataItem
import uz.infinityandro.taskguidebook.data.User
import uz.infinityandro.taskguidebook.data.UserNetwork
import uz.infinityandro.taskguidebook.domain.room.UserDao
import uz.infinityandro.taskguidebook.domain.room.UserDatabase
import uz.infinityandro.taskguidebook.domain.usecase.GuideBookUseCase
import uz.infinityandro.taskguidebook.presentation.viewModel.BookViewModel
import uz.infinityandro.taskguidebook.util.isConnected
import javax.inject.Inject

@HiltViewModel
class BookViewModelImpl @Inject constructor(
    private val useCase: GuideBookUseCase,
) : ViewModel(), BookViewModel {
    override val errorMessageLiveData = MutableLiveData<String>()
    override val connectionLiveData = MutableLiveData<Boolean>()
    override val allDataLiveData = MutableLiveData<List<DataItem>>()
    override val progressLiveData = MutableLiveData<Boolean>()
//    var dao=UserDatabase.getInstance(context).getDao()

    override fun getAllBooks(count: Int) {
        if (!isConnected()) {
            connectionLiveData.postValue(true)
            return
        }
        connectionLiveData.postValue(false)
        useCase.getData().onEach {
            progressLiveData.postValue(true)
            it.onSuccess { info ->
                progressLiveData.postValue(false)
                getListA(info, count)
            }.onFailure {
                errorMessageLiveData.postValue(it.message)
            }
        }.launchIn(viewModelScope)
    }

    override fun getListA(list: UserNetwork, count: Int) {


            if (count <= list.data!!.size) {
                var list1 = ArrayList<DataItem>()
                var list2 = ArrayList<User>()
                list1.addAll(list.data.subList(0, count))
                allDataLiveData.postValue(list1)
                list1.forEach {
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
//                viewModelScope.launch(Dispatchers.IO) {
//                    dao.insert(list2)
//                }
            }

    }
}