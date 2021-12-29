package uz.infinityandro.taskguidebook.presentation.ui.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.infinityandro.taskguidebook.R
import uz.infinityandro.taskguidebook.app.App
import uz.infinityandro.taskguidebook.data.User
import uz.infinityandro.taskguidebook.databinding.ScreenSavedBinding
import uz.infinityandro.taskguidebook.domain.room.UserDatabase
import uz.infinityandro.taskguidebook.presentation.ui.adapter.UserDataAdapter
import uz.infinityandro.taskguidebook.presentation.viewModel.Impl.UserDataViewModelImpl
import uz.infinityandro.taskguidebook.presentation.viewModel.UserDataViewModel
import uz.infinityandro.taskguidebook.util.InternetBroadCast
import uz.infinityandro.worldnews.utils.showToast

@AndroidEntryPoint
class SavedScreen : Fragment(R.layout.screen_saved) {
    private val binding by viewBinding(ScreenSavedBinding::bind)
    private val viewModel: UserDataViewModel by viewModels<UserDataViewModelImpl>()
    private var list = ArrayList<User>()
    private lateinit var adapter: UserDataAdapter
    private val receiver = InternetBroadCast()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
        if (receiver.isConnectedOrConnecting(requireContext())){
            viewModel.getAllBooks()
        }
        receiver.setListener {
            if (it) {
                viewModel.getAllBooks()
            } else {

            }
        }
        viewModel.getAllBooks()
        adapter = UserDataAdapter(list) { result ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://guidebook.com/${result.url}")
            startActivity(intent)
        }
        recycler.adapter = adapter
        recycler.layoutManager=LinearLayoutManager(requireContext())
        observers()
    }

    fun dialog(){
        val dialog = AlertDialog.Builder(requireContext()).create()
        dialog.setTitle("Network")
        dialog.setMessage("Open online mode!")
        dialog.setCanceledOnTouchOutside(false)
        dialog.setButton(
            AlertDialog.BUTTON_NEGATIVE,
            "Cancel",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        dialog.setButton(
            AlertDialog.BUTTON_POSITIVE,
            "Ok",
            DialogInterface.OnClickListener { dialog, which ->
                findNavController().navigate(R.id.mainScreen)
                dialog.dismiss()
            })
        dialog.show()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun observers() = with(binding) {
        viewModel.connectionLiveData.observe(requireActivity(),{
            if (it){
                dialog()

            }else{
            }
        })
        viewModel.progressLiveData.observe(requireActivity(), {
            if (it) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        })
        viewModel.errorMessageLiveData.observe(requireActivity(), {
            showToast("Error:$it")
        })
        viewModel.allDataLiveData.observe(requireActivity(), {
                list.addAll(it)
                adapter.notifyDataSetChanged()
        })


    }
}