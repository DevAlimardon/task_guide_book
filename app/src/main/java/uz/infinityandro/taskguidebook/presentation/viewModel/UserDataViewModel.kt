package uz.infinityandro.taskguidebook.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.infinityandro.taskguidebook.data.DataItem
import uz.infinityandro.taskguidebook.data.User

interface UserDataViewModel {
    val errorMessageLiveData: LiveData<String>
    val connectionLiveData: LiveData<Boolean>
    val allDataLiveData: LiveData<List<User>>
    val progressLiveData: LiveData<Boolean>

    fun getAllBooks()
}