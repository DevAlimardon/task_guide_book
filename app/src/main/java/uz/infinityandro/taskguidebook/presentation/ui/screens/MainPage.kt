package uz.infinityandro.taskguidebook.presentation.ui.screens

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.infinityandro.taskguidebook.R
import uz.infinityandro.taskguidebook.data.DataItem
import uz.infinityandro.taskguidebook.databinding.PageMainBinding

@AndroidEntryPoint
class MainPage:Fragment(R.layout.page_main) {
    private val binding by viewBinding(PageMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding){
        super.onViewCreated(view, savedInstanceState)
        val url=requireArguments().get("sava") as DataItem
        webPage.settings.javaScriptEnabled=true
        webPage.settings.domStorageEnabled=true
        webPage.settings.setSupportZoom(true)
        webPage.settings.builtInZoomControls=true
        webPage.settings.userAgentString="Android"
        webPage.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webPage.settings.cacheMode=WebSettings.LOAD_NO_CACHE
        webPage.settings.useWideViewPort=false
        webPage.loadUrl("https://www.amazon.co.uk/")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            webPage.webViewClient=object :WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    view?.loadUrl("https://www.amazon.co.uk/")
                    return false
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressWeb.visibility=View.GONE
                }
            }
        }
    }
}