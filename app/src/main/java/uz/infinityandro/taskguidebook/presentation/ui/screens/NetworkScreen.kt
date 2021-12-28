package uz.infinityandro.taskguidebook.presentation.ui.screens

import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.infinityandro.taskguidebook.R
import uz.infinityandro.taskguidebook.databinding.ScreenNetworkBinding
import uz.infinityandro.taskguidebook.util.InternetBroadCast

@AndroidEntryPoint
class NetworkScreen:Fragment(R.layout.screen_network) {
    private val receiver = InternetBroadCast()
    private val binding by viewBinding(ScreenNetworkBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
        receiver.setListener {
            if (it){

            }else{

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()){

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
    }
    private fun listeners() {
        binding.offline.setOnClickListener {
            findNavController().navigate(R.id.savedScreen)
        }
    }
}