package uz.infinityandro.taskguidebook.presentation.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.infinityandro.taskguidebook.R
import uz.infinityandro.taskguidebook.data.DataItem
import uz.infinityandro.taskguidebook.databinding.ScreenMainBinding
import uz.infinityandro.taskguidebook.presentation.ui.adapter.BookAdapter
import uz.infinityandro.taskguidebook.presentation.viewModel.BookViewModel
import uz.infinityandro.taskguidebook.presentation.viewModel.Impl.BookViewModelImpl
import uz.infinityandro.taskguidebook.util.InternetBroadCast
import uz.infinityandro.worldnews.utils.showToast

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: BookViewModel by viewModels<BookViewModelImpl>()
    private var list = ArrayList<DataItem>()
    private lateinit var adapter: BookAdapter
    var count = 3
    private val receiver = InternetBroadCast()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
        receiver.setListener {
            if (it){
                viewModel.getAllBooks(count)
            }else{
            findNavController().navigate(R.id.networkScreen)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        setItems()


    }

    private fun setItems() = with(binding) {
        adapter = BookAdapter(list) {result->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://guidebook.com/${result.url}"))
            startActivity(intent)
        }
        recycler.adapter = adapter
        observers()
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recycler.canScrollVertically(1)) {
                    count += 3
                    progress.visibility = View.VISIBLE
                    change()
                }
            }
        })
        change()
    }

    private fun change() {
        viewModel.getAllBooks(count)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observers() = with(binding) {
        viewModel.progressLiveData.observe(requireActivity(), {
            if (it) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        })
        viewModel.errorMessageLiveData.observe(requireActivity(), {

        })
        viewModel.allDataLiveData.observe(requireActivity(), {
            if (it != null) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

    }
}