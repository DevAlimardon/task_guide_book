package uz.infinityandro.taskguidebook.presentation.viewModel.Impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.infinityandro.taskguidebook.data.User
import uz.infinityandro.taskguidebook.domain.repository.UserDataRepository
import uz.infinityandro.taskguidebook.domain.usecase.UserDataUseCase
import uz.infinityandro.taskguidebook.presentation.viewModel.UserDataViewModel
import uz.infinityandro.taskguidebook.util.isConnected
import javax.inject.Inject

@HiltViewModel
class UserDataViewModelImpl @Inject constructor(
    private val userDataUseCase: UserDataUseCase) :
    ViewModel(), UserDataViewModel {
    override val errorMessageLiveData = MutableLiveData<String>()
    override val connectionLiveData = MutableLiveData<Boolean>()
    override val allDataLiveData = MutableLiveData<List<User>>()
    override val progressLiveData = MutableLiveData<Boolean>()

    override fun getAllBooks() {
        if (isConnected()){
            connectionLiveData.postValue(true)
        }else{
        connectionLiveData.postValue(false)}
        userDataUseCase.getData().onEach {
            progressLiveData.postValue(true)
            it.onSuccess {data->
                progressLiveData.postValue(false)
                allDataLiveData.postValue(data)
            }.onFailure {
                errorMessageLiveData.postValue(it.message)
            }
        }.launchIn(viewModelScope)
    }
}