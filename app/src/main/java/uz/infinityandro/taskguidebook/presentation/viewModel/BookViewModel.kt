package uz.infinityandro.taskguidebook.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.infinityandro.taskguidebook.data.DataItem
import uz.infinityandro.taskguidebook.data.UserNetwork

interface BookViewModel {
    val errorMessageLiveData: LiveData<String>
    val connectionLiveData: LiveData<Boolean>
    val allDataLiveData: LiveData<List<DataItem>>
    val progressLiveData: LiveData<Boolean>

    fun getAllBooks(count:Int)
    fun getListA(list: UserNetwork,count:Int)
}